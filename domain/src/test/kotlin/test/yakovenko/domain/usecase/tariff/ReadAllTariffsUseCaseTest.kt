package test.yakovenko.domain.usecase.tariff

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import test.yakovenko.domain.model.Tariff
import test.yakovenko.domain.model.TestTariff
import test.yakovenko.domain.repository.TariffsRepository
import java.util.UUID

class ReadAllTariffsUseCaseTest {
    private lateinit var readAllTariffsUseCase: ReadAllTariffsUseCase

    private val tariffsRepository: TariffsRepository = mockk()

    @BeforeEach
    fun setUp() {
        readAllTariffsUseCase = ReadAllTariffsUseCase(tariffsRepository)
    }

    @Test
    fun `should read all tariffs successfully`() = runTest {
        // Arrange
        val tariffs = listOf(
            TestTariff.create(operatorId = UUID.randomUUID()),
            TestTariff.create(operatorId = UUID.randomUUID())
        )

        coEvery { tariffsRepository.readAll() } returns tariffs

        // Act
        val result = readAllTariffsUseCase()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(tariffs, result.getOrNull())

        coVerify(exactly = 1) { tariffsRepository.readAll() }
    }

    @Test
    fun `should return empty collection when no tariffs exist`() = runTest {
        // Arrange
        coEvery { tariffsRepository.readAll() } returns emptyList<Tariff>()

        // Act
        val result = readAllTariffsUseCase()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(emptyList<Tariff>(), result.getOrNull())

        coVerify(exactly = 1) { tariffsRepository.readAll() }
    }
}