package usecase.service

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
import repository.ServicesRepository
import java.util.*

class DeleteServiceUseCaseTest {
    private lateinit var deleteServiceUseCase: DeleteServiceUseCase

    private val servicesRepository: ServicesRepository = mockk()

    private val serviceId = UUID.randomUUID()

    @BeforeEach
    fun setUp() {
        deleteServiceUseCase = DeleteServiceUseCase(servicesRepository)
    }

    @Test
    fun `should delete service successfully`() = runTest {
        // Arrange
        coEvery { servicesRepository.exists(serviceId) } returns true
        coEvery { servicesRepository.delete(serviceId) } returns true

        // Act
        val result = deleteServiceUseCase(serviceId)

        // Assert
        assertTrue(result.isSuccess)

        coVerifySequence {
            servicesRepository.exists(serviceId)
            servicesRepository.delete(serviceId)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when service does not exist`() = runTest {
        // Arrange
        coEvery { servicesRepository.exists(serviceId) } returns false

        // Act
        val result = deleteServiceUseCase(serviceId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerify(exactly = 1) { servicesRepository.exists(serviceId) }
        coVerify(exactly = 0) { servicesRepository.delete(any()) }
    }

    @Test
    fun `should fail with ModelDeleteException when delete returns false`() = runTest {
        // Arrange
        coEvery { servicesRepository.exists(serviceId) } returns true
        coEvery { servicesRepository.delete(serviceId) } returns false

        // Act
        val result = deleteServiceUseCase(serviceId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelDeleteException)

        coVerifySequence {
            servicesRepository.exists(serviceId)
            servicesRepository.delete(serviceId)
        }
    }
}