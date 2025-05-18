package test.yakovenko.domain.usecase.operator

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import test.yakovenko.domain.model.Operator
import test.yakovenko.domain.model.TestOperator
import test.yakovenko.domain.repository.OperatorsRepository

class ReadAllOperatorsUseCaseTest {
    private lateinit var readAllOperatorsUseCase: ReadAllOperatorsUseCase

    private val operatorsRepository: OperatorsRepository = mockk()

    @BeforeEach
    fun setUp() {
        readAllOperatorsUseCase = ReadAllOperatorsUseCase(operatorsRepository)
    }

    @Test
    fun `should read all operators successfully`() = runTest {
        // Arrange
        val operators = listOf(TestOperator.create(), TestOperator.create())

        coEvery { operatorsRepository.readAll() } returns operators

        // Act
        val result = readAllOperatorsUseCase()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(operators, result.getOrNull())

        coVerify(exactly = 1) { operatorsRepository.readAll() }
    }

    @Test
    fun `should return empty collection when no operators exist`() = runTest {
        // Arrange
        coEvery { operatorsRepository.readAll() } returns emptyList<Operator>()

        // Act
        val result = readAllOperatorsUseCase()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(emptyList<Operator>(), result.getOrNull())

        coVerify(exactly = 1) { operatorsRepository.readAll() }
    }
}