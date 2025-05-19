package usecase.operator

import exception.ModelDeleteException
import exception.ModelNotFoundException
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import repository.OperatorsRepository
import java.util.*

class DeleteOperatorUseCaseTest {
    private lateinit var deleteOperatorUseCase: DeleteOperatorUseCase

    private val operatorsRepository: OperatorsRepository = mockk()

    private val operatorId = UUID.randomUUID()

    @BeforeEach
    fun setUp() {
        deleteOperatorUseCase = DeleteOperatorUseCase(operatorsRepository)
    }

    @Test
    fun `should delete operator successfully`() = runTest {
        // Arrange
        coEvery { operatorsRepository.exists(operatorId) } returns true
        coEvery { operatorsRepository.delete(operatorId) } returns true

        // Act
        val result = deleteOperatorUseCase(operatorId)

        // Assert
        assertTrue(result.isSuccess)

        coVerifySequence {
            operatorsRepository.exists(operatorId)
            operatorsRepository.delete(operatorId)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when operator does not exist`() = runTest {
        // Arrange
        coEvery { operatorsRepository.exists(operatorId) } returns false

        // Act
        val result = deleteOperatorUseCase(operatorId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerify(exactly = 1) { operatorsRepository.exists(operatorId) }
        coVerify(exactly = 0) { operatorsRepository.delete(any()) }
    }

    @Test
    fun `should fail with ModelDeleteException when delete returns false`() = runTest {
        // Arrange
        coEvery { operatorsRepository.exists(operatorId) } returns true
        coEvery { operatorsRepository.delete(operatorId) } returns false

        // Act
        val result = deleteOperatorUseCase(operatorId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelDeleteException)

        coVerifySequence {
            operatorsRepository.exists(operatorId)
            operatorsRepository.delete(operatorId)
        }
    }
}