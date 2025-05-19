package usecase.tariff

import exception.ModelNotFoundException
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import model.Tariff
import model.TestTariff
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import repository.OperatorsRepository
import repository.TariffsRepository
import java.util.*

class FindTariffsByOperatorUseCaseTest {
    private lateinit var findTariffsByOperatorUseCase: FindTariffsByOperatorUseCase

    private val tariffsRepository: TariffsRepository = mockk()
    private val operatorsRepository: OperatorsRepository = mockk()

    private val operatorId = UUID.randomUUID()

    @BeforeEach
    fun setUp() {
        findTariffsByOperatorUseCase = FindTariffsByOperatorUseCase(
            tariffsRepository, operatorsRepository
        )
    }

    @Test
    fun `should find tariffs for existing operator`() = runTest {
        // Arrange
        val tariffs = listOf(
            TestTariff.create(operatorId = operatorId),
            TestTariff.create(operatorId = operatorId)
        )

        coEvery { operatorsRepository.exists(operatorId) } returns true
        coEvery { tariffsRepository.findByOperator(operatorId) } returns tariffs

        // Act
        val result = findTariffsByOperatorUseCase(operatorId)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(tariffs, result.getOrNull())

        coVerifySequence {
            operatorsRepository.exists(operatorId)
            tariffsRepository.findByOperator(operatorId)
        }
    }

    @Test
    fun `should return empty collection when operator has no tariffs`() = runTest {
        // Arrange
        coEvery { operatorsRepository.exists(operatorId) } returns true
        coEvery { tariffsRepository.findByOperator(operatorId) } returns emptyList<Tariff>()

        // Act
        val result = findTariffsByOperatorUseCase(operatorId)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(emptyList<Tariff>(), result.getOrNull())

        coVerifySequence {
            operatorsRepository.exists(operatorId)
            tariffsRepository.findByOperator(operatorId)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when operator does not exist`() = runTest {
        // Arrange
        coEvery { operatorsRepository.exists(operatorId) } returns false

        // Act
        val result = findTariffsByOperatorUseCase(operatorId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerify(exactly = 1) { operatorsRepository.exists(operatorId) }
        coVerify(exactly = 0) { tariffsRepository.findByOperator(any()) }
    }
}