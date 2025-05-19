package usecase.tariff

import exception.ModelNotFoundException
import exception.ModelUpdateException
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import model.TestTariff
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import repository.OperatorsRepository
import repository.TariffsRepository
import java.util.*

class UpdateTariffUseCaseTest {
    private lateinit var updateTariffUseCase: UpdateTariffUseCase

    private val tariffsRepository: TariffsRepository = mockk()
    private val operatorsRepository: OperatorsRepository = mockk()

    private val tariff = TestTariff.create(operatorId = UUID.randomUUID())

    @BeforeEach
    fun setUp() {
        updateTariffUseCase = UpdateTariffUseCase(tariffsRepository, operatorsRepository)
    }

    @Test
    fun `should update tariff successfully`() = runTest {
        // Arrange
        coEvery { tariffsRepository.exists(tariff.id) } returns true
        coEvery { operatorsRepository.exists(tariff.operatorId) } returns true
        coEvery { tariffsRepository.update(tariff) } returns tariff

        // Act
        val result = updateTariffUseCase(tariff)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(tariff, result.getOrNull())

        coVerifySequence {
            tariffsRepository.exists(tariff.id)
            operatorsRepository.exists(tariff.operatorId)
            tariffsRepository.update(tariff)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when tariff does not exist`() = runTest {
        // Arrange
        coEvery { tariffsRepository.exists(tariff.id) } returns false

        // Act
        val result = updateTariffUseCase(tariff)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerify(exactly = 1) { tariffsRepository.exists(tariff.id) }
        coVerify(exactly = 0) { operatorsRepository.exists(any()) }
        coVerify(exactly = 0) { tariffsRepository.update(any()) }
    }

    @Test
    fun `should fail with ModelNotFoundException when operator does not exist`() = runTest {
        // Arrange
        coEvery { tariffsRepository.exists(tariff.id) } returns true
        coEvery { operatorsRepository.exists(tariff.operatorId) } returns false

        // Act
        val result = updateTariffUseCase(tariff)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerifySequence {
            tariffsRepository.exists(tariff.id)
            operatorsRepository.exists(tariff.operatorId)
        }
        coVerify(exactly = 0) { tariffsRepository.update(any()) }
    }

    @Test
    fun `should fail with ModelUpdateException when update returns null`() = runTest {
        // Arrange
        coEvery { tariffsRepository.exists(tariff.id) } returns true
        coEvery { operatorsRepository.exists(tariff.operatorId) } returns true
        coEvery { tariffsRepository.update(tariff) } returns null

        // Act
        val result = updateTariffUseCase(tariff)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelUpdateException)

        coVerifySequence {
            tariffsRepository.exists(tariff.id)
            operatorsRepository.exists(tariff.operatorId)
            tariffsRepository.update(tariff)
        }
    }
}
