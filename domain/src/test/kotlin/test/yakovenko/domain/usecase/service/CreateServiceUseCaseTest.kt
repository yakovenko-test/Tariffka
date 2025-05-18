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
import test.yakovenko.domain.exception.ModelCreateException
import test.yakovenko.domain.exception.ModelDuplicateException
import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.model.TestService
import test.yakovenko.domain.repository.OperatorsRepository
import test.yakovenko.domain.repository.ServicesRepository
import java.util.UUID

class CreateServiceUseCaseTest {
    private lateinit var createServiceUseCase: CreateServiceUseCase

    private val servicesRepository: ServicesRepository = mockk()
    private val operatorsRepository: OperatorsRepository = mockk()

    private val service = TestService.create(operatorId = UUID.randomUUID())

    @BeforeEach
    fun setUp() {
        createServiceUseCase = CreateServiceUseCase(servicesRepository, operatorsRepository)
    }

    @Test
    fun `should create service successfully`() = runTest {
        // Arrange
        coEvery { servicesRepository.exists(service.id) } returns false
        coEvery { operatorsRepository.exists(service.operatorId) } returns true
        coEvery { servicesRepository.create(service) } returns service

        // Act
        val result = createServiceUseCase(service)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(service, result.getOrNull())

        coVerifySequence {
            servicesRepository.exists(service.id)
            operatorsRepository.exists(service.operatorId)
            servicesRepository.create(service)
        }
    }

    @Test
    fun `should fail with ModelDuplicateException when service already exists`() = runTest {
        // Arrange
        coEvery { servicesRepository.exists(service.id) } returns true

        // Act
        val result = createServiceUseCase(service)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelDuplicateException)

        coVerify(exactly = 1) { servicesRepository.exists(service.id) }
        coVerify(exactly = 0) { operatorsRepository.exists(any()) }
        coVerify(exactly = 0) { servicesRepository.create(any()) }
    }

    @Test
    fun `should fail with ModelNotFoundException when operator does not exist`() = runTest {
        // Arrange
        coEvery { servicesRepository.exists(service.id) } returns false
        coEvery { operatorsRepository.exists(service.operatorId) } returns false

        // Act
        val result = createServiceUseCase(service)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerifySequence {
            servicesRepository.exists(service.id)
            operatorsRepository.exists(service.operatorId)
        }
        coVerify(exactly = 0) { servicesRepository.create(any()) }
    }

    @Test
    fun `should fail with ModelCreateException when create returns null`() = runTest {
        // Arrange
        coEvery { servicesRepository.exists(service.id) } returns false
        coEvery { operatorsRepository.exists(service.operatorId) } returns true
        coEvery { servicesRepository.create(service) } returns null

        // Act
        val result = createServiceUseCase(service)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelCreateException)

        coVerifySequence {
            servicesRepository.exists(service.id)
            operatorsRepository.exists(service.operatorId)
            servicesRepository.create(service)
        }
    }
}