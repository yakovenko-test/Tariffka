package usecase.service

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import model.Service
import model.TestService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import repository.ServicesRepository
import java.util.*

class ReadAllServicesUseCaseTest {
    private lateinit var readAllServicesUseCase: ReadAllServicesUseCase

    private val servicesRepository: ServicesRepository = mockk()

    @BeforeEach
    fun setUp() {
        readAllServicesUseCase = ReadAllServicesUseCase(servicesRepository)
    }

    @Test
    fun `should read all services successfully`() = runTest {
        // Arrange
        val services = listOf(
            TestService.create(operatorId = UUID.randomUUID()),
            TestService.create(operatorId = UUID.randomUUID())
        )

        coEvery { servicesRepository.readAll() } returns services

        // Act
        val result = readAllServicesUseCase()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(services, result.getOrNull())

        coVerify(exactly = 1) { servicesRepository.readAll() }
    }

    @Test
    fun `should return empty collection when no services exist`() = runTest {
        // Arrange
        coEvery { servicesRepository.readAll() } returns emptyList<Service>()

        // Act
        val result = readAllServicesUseCase()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(emptyList<Service>(), result.getOrNull())

        coVerify(exactly = 1) { servicesRepository.readAll() }
    }
}