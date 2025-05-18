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
import test.yakovenko.domain.exception.ModelUpdateException
import test.yakovenko.domain.model.TestService
import test.yakovenko.domain.repository.OperatorsRepository
import test.yakovenko.domain.repository.ServicesRepository
import java.util.UUID

class UpdateServiceUseCaseTest {
    private lateinit var updateServiceUseCase: UpdateServiceUseCase

    private val servicesRepository: ServicesRepository = mockk()
    private val operatorsRepository: OperatorsRepository = mockk()

    private val service = TestService.create(operatorId = UUID.randomUUID())

    @BeforeEach
    fun setUp() {
        updateServiceUseCase = UpdateServiceUseCase(servicesRepository, operatorsRepository)
    }

    @Test
    fun `should update service successfully`() = runTest {
        // Arrange
        coEvery { servicesRepository.exists(service.id) } returns true
        coEvery { operatorsRepository.exists(service.operatorId) } returns true
        coEvery { servicesRepository.update(service) } returns service

        // Act
        val result = updateServiceUseCase(service)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(service, result.getOrNull())

        coVerifySequence {
            servicesRepository.exists(service.id)
            operatorsRepository.exists(service.operatorId)
            servicesRepository.update(service)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when service does not exist`() = runTest {
        // Arrange
        coEvery { servicesRepository.exists(service.id) } returns false

        // Act
        val result = updateServiceUseCase(service)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerify(exactly = 1) { servicesRepository.exists(service.id) }
        coVerify(exactly = 0) { operatorsRepository.exists(any()) }
        coVerify(exactly = 0) { servicesRepository.update(any()) }
    }

    @Test
    fun `should fail with ModelNotFoundException when operator does not exist`() = runTest {
        // Arrange
        coEvery { servicesRepository.exists(service.id) } returns true
        coEvery { operatorsRepository.exists(service.operatorId) } returns false

        // Act
        val result = updateServiceUseCase(service)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerifySequence {
            servicesRepository.exists(service.id)
            operatorsRepository.exists(service.operatorId)
        }
        coVerify(exactly = 0) { servicesRepository.update(any()) }
    }

    @Test
    fun `should fail with ModelUpdateException when update returns null`() = runTest {
        // Arrange
        coEvery { servicesRepository.exists(service.id) } returns true
        coEvery { operatorsRepository.exists(service.operatorId) } returns true
        coEvery { servicesRepository.update(service) } returns null

        // Act
        val result = updateServiceUseCase(service)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelUpdateException)

        coVerifySequence {
            servicesRepository.exists(service.id)
            operatorsRepository.exists(service.operatorId)
            servicesRepository.update(service)
        }
    }
}