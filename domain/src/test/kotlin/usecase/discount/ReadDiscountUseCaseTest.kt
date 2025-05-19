package usecase.discount

import exception.ModelNotFoundException
import exception.ModelReadException
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import model.TestDiscount
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import repository.DiscountsRepository
import java.util.*

class ReadDiscountUseCaseTest {
    private lateinit var readDiscountUseCase: ReadDiscountUseCase

    private val discountsRepository: DiscountsRepository = mockk()

    private val discountId = UUID.randomUUID()

    @BeforeEach
    fun setUp() {
        readDiscountUseCase = ReadDiscountUseCase(discountsRepository)
    }

    @Test
    fun `should read discount successfully`() = runTest {
        // Arrange
        val discount = TestDiscount.create(id = discountId, operatorId = UUID.randomUUID())

        coEvery { discountsRepository.exists(discountId) } returns true
        coEvery { discountsRepository.read(discountId) } returns discount

        // Act
        val result = readDiscountUseCase(discountId)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(discount, result.getOrNull())

        coVerifySequence {
            discountsRepository.exists(discountId)
            discountsRepository.read(discountId)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when discount does not exist`() = runTest {
        // Arrange
        coEvery { discountsRepository.exists(discountId) } returns false

        // Act
        val result = readDiscountUseCase(discountId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerify(exactly = 1) { discountsRepository.exists(discountId) }
        coVerify(exactly = 0) { discountsRepository.read(any()) }
    }

    @Test
    fun `should fail with ModelReadException when read returns null`() = runTest {
        // Arrange
        coEvery { discountsRepository.exists(discountId) } returns true
        coEvery { discountsRepository.read(discountId) } returns null

        // Act
        val result = readDiscountUseCase(discountId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelReadException)

        coVerifySequence {
            discountsRepository.exists(discountId)
            discountsRepository.read(discountId)
        }
    }
}