package test.yakovenko.domain.usecase.tariff

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import test.yakovenko.domain.exception.ModelDeleteException
import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.repository.TariffsRepository
import java.util.UUID

class DeleteTariffUseCaseTest {
    private lateinit var deleteTariffUseCase: DeleteTariffUseCase

    private val tariffsRepository: TariffsRepository = mockk()

    private val tariffId = UUID.randomUUID()

    @BeforeEach
    fun setUp() {
        deleteTariffUseCase = DeleteTariffUseCase(tariffsRepository)
    }

    @Test
    fun `should delete tariff successfully`() = runTest {
        // Arrange
        coEvery { tariffsRepository.exists(tariffId) } returns true
        coEvery { tariffsRepository.delete(tariffId) } returns true

        // Act
        val result = deleteTariffUseCase(tariffId)

        // Assert
        assertTrue(result.isSuccess)

        coVerifySequence {
            tariffsRepository.exists(tariffId)
            tariffsRepository.delete(tariffId)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when tariff does not exist`() = runTest {
        // Arrange
        coEvery { tariffsRepository.exists(tariffId) } returns false

        // Act
        val result = deleteTariffUseCase(tariffId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerify(exactly = 1) { tariffsRepository.exists(tariffId) }
        coVerify(exactly = 0) { tariffsRepository.delete(any()) }
    }

    @Test
    fun `should fail with when ModelDeleteException delete returns false`() = runTest {
        // Arrange
        coEvery { tariffsRepository.exists(tariffId) } returns true
        coEvery { tariffsRepository.delete(tariffId) } returns false

        // Act
        val result = deleteTariffUseCase(tariffId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelDeleteException)

        coVerifySequence {
            tariffsRepository.exists(tariffId)
            tariffsRepository.delete(tariffId)
        }
    }
}
