package usecase.service

import exception.ModelNotFoundException
import exception.ModelReadException
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import model.TestService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import repository.ServicesRepository
import java.util.*

class ReadServiceUseCaseTest {
    private lateinit var readServiceUseCase: ReadServiceUseCase

    private val servicesRepository: ServicesRepository = mockk()

    private val serviceId = UUID.randomUUID()

    @BeforeEach
    fun setUp() {
        readServiceUseCase = ReadServiceUseCase(servicesRepository)
    }

    @Test
    fun `should read service successfully`() = runTest {
        // Arrange
        val service = TestService.create(id = serviceId, operatorId = UUID.randomUUID())

        coEvery { servicesRepository.exists(serviceId) } returns true
        coEvery { servicesRepository.read(serviceId) } returns service

        // Act
        val result = readServiceUseCase(serviceId)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(service, result.getOrNull())

        coVerifySequence {
            servicesRepository.exists(serviceId)
            servicesRepository.read(serviceId)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when service does not exist`() = runTest {
        // Arrange
        coEvery { servicesRepository.exists(serviceId) } returns false

        // Act
        val result = readServiceUseCase(serviceId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerify(exactly = 1) { servicesRepository.exists(serviceId) }
        coVerify(exactly = 0) { servicesRepository.read(any()) }
    }

    @Test
    fun `should fail with ModelReadException when read returns null`() = runTest {
        // Arrange
        coEvery { servicesRepository.exists(serviceId) } returns true
        coEvery { servicesRepository.read(serviceId) } returns null

        // Act
        val result = readServiceUseCase(serviceId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelReadException)

        coVerifySequence {
            servicesRepository.exists(serviceId)
            servicesRepository.read(serviceId)
        }
    }
}