package test.yakovenko.domain.usecase.tariff

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
import test.yakovenko.domain.model.TestTariff
import test.yakovenko.domain.repository.TariffsRepository
import java.util.UUID

class ReadTariffUseCaseTest {
    private lateinit var readTariffUseCase: ReadTariffUseCase

    private val tariffsRepository: TariffsRepository = mockk()

    private val tariffId = UUID.randomUUID()

    @BeforeEach
    fun setUp() {
        readTariffUseCase = ReadTariffUseCase(tariffsRepository)
    }

    @Test
    fun `should read tariff successfully`() = runTest {
        // Arrange
        val tariff = TestTariff.create(id = tariffId, operatorId = UUID.randomUUID())

        coEvery { tariffsRepository.exists(tariffId) } returns true
        coEvery { tariffsRepository.read(tariffId) } returns tariff

        // Act
        val result = readTariffUseCase(tariffId)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(tariff, result.getOrNull())

        coVerifySequence {
            tariffsRepository.exists(tariffId)
            tariffsRepository.read(tariffId)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when tariff does not exist`() = runTest {
        // Arrange
        coEvery { tariffsRepository.exists(tariffId) } returns false

        // Act
        val result = readTariffUseCase(tariffId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerify(exactly = 1) { tariffsRepository.exists(tariffId) }
        coVerify(exactly = 0) { tariffsRepository.read(any()) }
    }

    @Test
    fun `should fail with ModelReadException when read returns null`() = runTest {
        // Arrange
        coEvery { tariffsRepository.exists(tariffId) } returns true
        coEvery { tariffsRepository.read(tariffId) } returns null

        // Act
        val result = readTariffUseCase(tariffId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelReadException)

        coVerifySequence {
            tariffsRepository.exists(tariffId)
            tariffsRepository.read(tariffId)
        }
    }
}