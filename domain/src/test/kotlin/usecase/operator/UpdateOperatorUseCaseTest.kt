package usecase.operator

import exception.ModelNotFoundException
import exception.ModelUpdateException
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import model.TestOperator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import repository.OperatorsRepository

class UpdateOperatorUseCaseTest {
    private lateinit var updateOperatorUseCase: UpdateOperatorUseCase

    private val operatorsRepository: OperatorsRepository = mockk()

    private val operator = TestOperator.create()

    @BeforeEach
    fun setUp() {
        updateOperatorUseCase = UpdateOperatorUseCase(operatorsRepository)
    }

    @Test
    fun `should update operator successfully`() = runTest {
        // Arrange
        coEvery { operatorsRepository.exists(operator.id) } returns true
        coEvery { operatorsRepository.update(operator) } returns operator

        // Act
        val result = updateOperatorUseCase(operator)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(operator, result.getOrNull())

        coVerifySequence {
            operatorsRepository.exists(operator.id)
            operatorsRepository.update(operator)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when operator does not exist`() = runTest {
        // Arrange
        coEvery { operatorsRepository.exists(operator.id) } returns false

        // Act
        val result = updateOperatorUseCase(operator)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerify(exactly = 1) { operatorsRepository.exists(operator.id) }
        coVerify(exactly = 0) { operatorsRepository.update(any()) }
    }

    @Test
    fun `should fail with ModelUpdateException when update returns null`() = runTest {
        // Arrange
        coEvery { operatorsRepository.exists(operator.id) } returns true
        coEvery { operatorsRepository.update(operator) } returns null

        // Act
        val result = updateOperatorUseCase(operator)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelUpdateException)

        coVerifySequence {
            operatorsRepository.exists(operator.id)
            operatorsRepository.update(operator)
        }
    }
}