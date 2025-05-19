package usecase.discount

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import model.Discount
import model.TestDiscount
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import repository.DiscountsRepository
import java.util.*

class ReadAllDiscountsUseCaseTest {
    private lateinit var readAllDiscountsUseCase: ReadAllDiscountsUseCase

    private val discountsRepository: DiscountsRepository = mockk()

    @BeforeEach
    fun setUp() {
        readAllDiscountsUseCase = ReadAllDiscountsUseCase(discountsRepository)
    }

    @Test
    fun `should read all discounts successfully`() = runTest {
        // Arrange
        val discounts = listOf(
            TestDiscount.create(operatorId = UUID.randomUUID()),
            TestDiscount.create(operatorId = UUID.randomUUID())
        )
        coEvery { discountsRepository.readAll() } returns discounts

        // Act
        val result = readAllDiscountsUseCase()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(discounts, result.getOrNull())

        coVerify(exactly = 1) { discountsRepository.readAll() }
    }

    @Test
    fun `should return empty collection when no discounts exist`() = runTest {
        // Arrange
        coEvery { discountsRepository.readAll() } returns emptyList<Discount>()

        // Act
        val result = readAllDiscountsUseCase()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(emptyList<Discount>(), result.getOrNull())

        coVerify(exactly = 1) { discountsRepository.readAll() }
    }
}