package test.yakovenko.domain.usecase.operator

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.exception.ModelReadException
import test.yakovenko.domain.model.TestOperator
import test.yakovenko.domain.repository.OperatorsRepository
import java.util.UUID

class ReadOperatorUseCaseTest {
    private lateinit var readOperatorUseCase: ReadOperatorUseCase

    private val operatorsRepository: OperatorsRepository = mockk()

    private val operatorId = UUID.randomUUID()

    @BeforeEach
    fun setUp() {
        readOperatorUseCase = ReadOperatorUseCase(operatorsRepository)
    }

    @Test
    fun `should read operator successfully`() = runTest {
        // Arrange
        val operator = TestOperator.create(id = operatorId)

        coEvery { operatorsRepository.exists(operatorId) } returns true
        coEvery { operatorsRepository.read(operatorId) } returns operator

        // Act
        val result = readOperatorUseCase(operatorId)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(operator, result.getOrNull())

        coVerifySequence {
            operatorsRepository.exists(operatorId)
            operatorsRepository.read(operatorId)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when operator does not exist`() = runTest {
        // Arrange
        coEvery { operatorsRepository.exists(operatorId) } returns false

        // Act
        val result = readOperatorUseCase(operatorId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerify(exactly = 1) { operatorsRepository.exists(operatorId) }
        coVerify(exactly = 0) { operatorsRepository.read(any()) }
    }

    @Test
    fun `should fail with ModelReadException when read returns null`() = runTest {
        // Arrange
        coEvery { operatorsRepository.exists(operatorId) } returns true
        coEvery { operatorsRepository.read(operatorId) } returns null

        // Act
        val result = readOperatorUseCase(operatorId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelReadException)

        coVerifySequence {
            operatorsRepository.exists(operatorId)
            operatorsRepository.read(operatorId)
        }
    }
}