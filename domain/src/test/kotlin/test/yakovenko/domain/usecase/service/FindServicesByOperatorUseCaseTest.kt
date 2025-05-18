package test.yakovenko.domain.usecase.service

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
import test.yakovenko.domain.model.Service
import test.yakovenko.domain.model.TestService
import test.yakovenko.domain.repository.OperatorsRepository
import test.yakovenko.domain.repository.ServicesRepository
import java.util.UUID

class FindServicesByOperatorUseCaseTest {
    private lateinit var findServicesByOperatorUseCase: FindServicesByOperatorUseCase

    private val servicesRepository: ServicesRepository = mockk()
    private val operatorsRepository: OperatorsRepository = mockk()

    private val operatorId = UUID.randomUUID()

    @BeforeEach
    fun setUp() {
        findServicesByOperatorUseCase = FindServicesByOperatorUseCase(
            servicesRepository, operatorsRepository
        )
    }

    @Test
    fun `should find services for existing operator`() = runTest {
        // Arrange
        val services = listOf(
            TestService.create(operatorId = operatorId),
            TestService.create(operatorId = operatorId)
        )

        coEvery { operatorsRepository.exists(operatorId) } returns true
        coEvery { servicesRepository.findByOperator(operatorId) } returns services

        // Act
        val result = findServicesByOperatorUseCase(operatorId)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(services, result.getOrNull())

        coVerifySequence {
            operatorsRepository.exists(operatorId)
            servicesRepository.findByOperator(operatorId)
        }
    }

    @Test
    fun `should return empty collection when operator has no services`() = runTest {
        // Arrange
        coEvery { operatorsRepository.exists(operatorId) } returns true
        coEvery { servicesRepository.findByOperator(operatorId) } returns emptyList<Service>()

        // Act
        val result = findServicesByOperatorUseCase(operatorId)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(emptyList<Service>(), result.getOrNull())

        coVerifySequence {
            operatorsRepository.exists(operatorId)
            servicesRepository.findByOperator(operatorId)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when operator does not exist`() = runTest {
        // Arrange
        coEvery { operatorsRepository.exists(operatorId) } returns false

        // Act
        val result = findServicesByOperatorUseCase(operatorId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerify(exactly = 1) { operatorsRepository.exists(operatorId) }
        coVerify(exactly = 0) { servicesRepository.findByOperator(any()) }
    }
}