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
import test.yakovenko.domain.exception.ModelCreateException
import test.yakovenko.domain.exception.ModelDuplicateException
import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.model.TestTariff
import test.yakovenko.domain.repository.OperatorsRepository
import test.yakovenko.domain.repository.TariffsRepository
import java.util.UUID

class CreateTariffUseCaseTest {
    private lateinit var createTariffUseCase: CreateTariffUseCase

    private val tariffsRepository: TariffsRepository = mockk()
    private val operatorsRepository: OperatorsRepository = mockk()

    private val tariff = TestTariff.create(operatorId = UUID.randomUUID())

    @BeforeEach
    fun setUp() {
        createTariffUseCase = CreateTariffUseCase(tariffsRepository, operatorsRepository)
    }

    @Test
    fun `should create tariff successfully`() = runTest {
        // Arrange
        coEvery { tariffsRepository.exists(tariff.id) } returns false
        coEvery { operatorsRepository.exists(tariff.operatorId) } returns true
        coEvery { tariffsRepository.create(tariff) } returns tariff

        // Act
        val result = createTariffUseCase(tariff)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(tariff, result.getOrNull())

        coVerifySequence {
            tariffsRepository.exists(tariff.id)
            operatorsRepository.exists(tariff.operatorId)
            tariffsRepository.create(tariff)
        }
    }

    @Test
    fun `should fail with ModelDuplicateException when tariff already exists`() = runTest {
        // Arrange
        coEvery { tariffsRepository.exists(tariff.id) } returns true

        // Act
        val result = createTariffUseCase(tariff)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelDuplicateException)

        coVerify(exactly = 1) { tariffsRepository.exists(tariff.id) }
        coVerify(exactly = 0) { operatorsRepository.exists(any()) }
        coVerify(exactly = 0) { tariffsRepository.create(any()) }
    }

    @Test
    fun `should fail with ModelNotFoundException when operator does not exist`() = runTest {
        // Arrange
        coEvery { tariffsRepository.exists(tariff.id) } returns false
        coEvery { operatorsRepository.exists(tariff.operatorId) } returns false

        // Act
        val result = createTariffUseCase(tariff)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerifySequence {
            tariffsRepository.exists(tariff.id)
            operatorsRepository.exists(tariff.operatorId)
        }
        coVerify(exactly = 0) { tariffsRepository.create(any()) }
    }

    @Test
    fun `should fail with ModelCreateException when create returns null`() = runTest {
        // Arrange
        coEvery { tariffsRepository.exists(tariff.id) } returns false
        coEvery { operatorsRepository.exists(tariff.operatorId) } returns true
        coEvery { tariffsRepository.create(tariff) } returns null

        // Act
        val result = createTariffUseCase(tariff)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelCreateException)

        coVerifySequence {
            tariffsRepository.exists(tariff.id)
            operatorsRepository.exists(tariff.operatorId)
            tariffsRepository.create(tariff)
        }
    }
}