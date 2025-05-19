package usecase.operator

import exception.ModelCreateException
import exception.ModelDuplicateException
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

class CreateOperatorUseCaseTest {
    private lateinit var createOperatorUseCase: CreateOperatorUseCase

    private val operatorsRepository: OperatorsRepository = mockk()

    private val operator = TestOperator.create()

    @BeforeEach
    fun setUp() {
        createOperatorUseCase = CreateOperatorUseCase(operatorsRepository)
    }

    @Test
    fun `should create operator successfully`() = runTest {
        // Arrange
        coEvery { operatorsRepository.exists(operator.id) } returns false
        coEvery { operatorsRepository.create(operator) } returns operator

        // Act
        val result = createOperatorUseCase(operator)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(operator, result.getOrNull())

        coVerifySequence {
            operatorsRepository.exists(operator.id)
            operatorsRepository.create(operator)
        }
    }

    @Test
    fun `should fail with ModelDuplicateException when operator already exists`() = runTest {
        // Arrange
        coEvery { operatorsRepository.exists(operator.id) } returns true

        // Act
        val result = createOperatorUseCase(operator)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelDuplicateException)

        coVerify(exactly = 1) { operatorsRepository.exists(operator.id) }
        coVerify(exactly = 0) { operatorsRepository.create(any()) }
    }

    @Test
    fun `should fail with ModelCreateException when create returns null`() = runTest {
        // Arrange
        coEvery { operatorsRepository.exists(operator.id) } returns false
        coEvery { operatorsRepository.create(operator) } returns null

        // Act
        val result = createOperatorUseCase(operator)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelCreateException)

        coVerifySequence {
            operatorsRepository.exists(operator.id)
            operatorsRepository.create(operator)
        }
    }
}
