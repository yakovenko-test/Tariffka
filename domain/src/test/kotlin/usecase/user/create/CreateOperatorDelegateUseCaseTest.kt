package usecase.user.create

import exception.ModelCreateException
import exception.ModelDuplicateException
import exception.ModelNotFoundException
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

class CreateOperatorDelegateUseCaseTest {
    private val usersRepository: UsersRepository = mockk()
    private val operatorsRepository: OperatorsRepository = mockk()

    private lateinit var createOperatorDelegateUseCase: CreateOperatorDelegateUseCase

    private val operatorDelegate = TestUser.createOperatorDelegate(operatorId = UUID.randomUUID())

    @BeforeEach
    fun setUp() {
        createOperatorDelegateUseCase = CreateOperatorDelegateUseCase(
            usersRepository, operatorsRepository
        )
    }

    @Test
    fun `should create operator delegate successfully`() = runTest {
        // Arrange
        coEvery { usersRepository.exists(operatorDelegate.id) } returns false
        coEvery { operatorsRepository.exists(operatorDelegate.operatorId) } returns true
        coEvery { usersRepository.create(operatorDelegate) } returns operatorDelegate

        // Act
        val result = createOperatorDelegateUseCase(operatorDelegate)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(operatorDelegate, result.getOrNull())

        coVerifySequence {
            usersRepository.exists(operatorDelegate.id)
            operatorsRepository.exists(operatorDelegate.operatorId)
            usersRepository.create(operatorDelegate)
        }
    }

    @Test
    fun `should fail with ModelDuplicateException when operator delegate already exists`() =
        runTest {
            // Arrange
            coEvery { usersRepository.exists(operatorDelegate.id) } returns true

            // Act
            val result = createOperatorDelegateUseCase(operatorDelegate)

            // Assert
            assertTrue(result.isFailure)
            assertTrue(result.exceptionOrNull() is ModelDuplicateException)

            coVerify(exactly = 1) { usersRepository.exists(operatorDelegate.id) }
            coVerify(exactly = 0) { operatorsRepository.exists(any()) }
            coVerify(exactly = 0) { usersRepository.create(any()) }
        }

    @Test
    fun `should fail with ModelNotFoundException when operator does not exist`() = runTest {
        // Arrange
        coEvery { usersRepository.exists(operatorDelegate.id) } returns false
        coEvery { operatorsRepository.exists(operatorDelegate.operatorId) } returns false

        // Act
        val result = createOperatorDelegateUseCase(operatorDelegate)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerifySequence {
            usersRepository.exists(operatorDelegate.id)
            operatorsRepository.exists(operatorDelegate.operatorId)
        }
        coVerify(exactly = 0) { usersRepository.create(any()) }
    }

    @Test
    fun `should fail with ModelCreateException when create returns null`() = runTest {
        // Arrange
        coEvery { usersRepository.exists(operatorDelegate.id) } returns false
        coEvery { operatorsRepository.exists(operatorDelegate.operatorId) } returns true
        coEvery { usersRepository.create(operatorDelegate) } returns null

        // Act
        val result = createOperatorDelegateUseCase(operatorDelegate)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelCreateException)

        coVerifySequence {
            usersRepository.exists(operatorDelegate.id)
            operatorsRepository.exists(operatorDelegate.operatorId)
            usersRepository.create(operatorDelegate)
        }
    }
}
