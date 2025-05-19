package usecase.user.update

import exception.ModelNotFoundException
import exception.ModelUpdateException
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import model.TestUser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import repository.OperatorsRepository
import repository.UsersRepository
import java.util.*

class UpdateOperatorDelegateUseCaseTest {
    private lateinit var updateOperatorDelegateUseCase: UpdateOperatorDelegateUseCase

    private val usersRepository: UsersRepository = mockk()
    private val operatorsRepository: OperatorsRepository = mockk()

    private val operatorDelegate = TestUser.createOperatorDelegate(operatorId = UUID.randomUUID())

    @BeforeEach
    fun setUp() {
        updateOperatorDelegateUseCase = UpdateOperatorDelegateUseCase(
            usersRepository, operatorsRepository
        )
    }

    @Test
    fun `should update operator delegate successfully`() = runTest {
        // Arrange
        coEvery { usersRepository.exists(operatorDelegate.id) } returns true
        coEvery { operatorsRepository.exists(operatorDelegate.operatorId) } returns true
        coEvery { usersRepository.update(operatorDelegate) } returns operatorDelegate

        // Act
        val result = updateOperatorDelegateUseCase(operatorDelegate)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(operatorDelegate, result.getOrNull())

        coVerifySequence {
            usersRepository.exists(operatorDelegate.id)
            operatorsRepository.exists(operatorDelegate.operatorId)
            usersRepository.update(operatorDelegate)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when operator delegate does not exist`() =
        runTest {
            // Arrange
            coEvery { usersRepository.exists(operatorDelegate.id) } returns false

            // Act
            val result = updateOperatorDelegateUseCase(operatorDelegate)

            // Assert
            assertTrue(result.isFailure)
            assertTrue(result.exceptionOrNull() is ModelNotFoundException)

            coVerify(exactly = 1) { usersRepository.exists(operatorDelegate.id) }
            coVerify(exactly = 0) { operatorsRepository.exists(any()) }
            coVerify(exactly = 0) { usersRepository.update(any()) }
        }

    @Test
    fun `should fail with ModelNotFoundException when operator does not exist`() = runTest {
        // Arrange
        coEvery { usersRepository.exists(operatorDelegate.id) } returns true
        coEvery { operatorsRepository.exists(operatorDelegate.operatorId) } returns false

        // Act
        val result = updateOperatorDelegateUseCase(operatorDelegate)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerifySequence {
            usersRepository.exists(operatorDelegate.id)
            operatorsRepository.exists(operatorDelegate.operatorId)
        }
        coVerify(exactly = 0) { usersRepository.update(any()) }
    }

    @Test
    fun `should fail with ModelUpdateException when update returns null`() = runTest {
        // Arrange
        coEvery { usersRepository.exists(operatorDelegate.id) } returns true
        coEvery { operatorsRepository.exists(operatorDelegate.operatorId) } returns true
        coEvery { usersRepository.update(operatorDelegate) } returns null

        // Act
        val result = updateOperatorDelegateUseCase(operatorDelegate)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelUpdateException)

        coVerifySequence {
            usersRepository.exists(operatorDelegate.id)
            operatorsRepository.exists(operatorDelegate.operatorId)
            usersRepository.update(operatorDelegate)
        }
    }
}
