package usecase.discount

import exception.ModelDeleteException
import exception.ModelNotFoundException
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import repository.DiscountsRepository
import java.util.*

class DeleteDiscountUseCaseTest {
    private lateinit var deleteDiscountUseCase: DeleteDiscountUseCase

    private val discountsRepository: DiscountsRepository = mockk()

    private val discountId = UUID.randomUUID()

    @BeforeEach
    fun setUp() {
        deleteDiscountUseCase = DeleteDiscountUseCase(discountsRepository)
    }

    @Test
    fun `should delete discount successfully`() = runTest {
        // Arrange
        coEvery { discountsRepository.exists(discountId) } returns true
        coEvery { discountsRepository.delete(discountId) } returns true

        // Act
        val result = deleteDiscountUseCase(discountId)

        // Assert
        assertTrue(result.isSuccess)

        coVerifySequence {
            discountsRepository.exists(discountId)
            discountsRepository.delete(discountId)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when discount does not exist`() = runTest {
        // Arrange
        coEvery { discountsRepository.exists(discountId) } returns false

        // Act
        val result = deleteDiscountUseCase(discountId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerify(exactly = 1) { discountsRepository.exists(discountId) }
        coVerify(exactly = 0) { discountsRepository.delete(any()) }
    }

    @Test
    fun `should fail with ModelDeleteException when delete returns false`() = runTest {
        // Arrange
        coEvery { discountsRepository.exists(discountId) } returns true
        coEvery { discountsRepository.delete(discountId) } returns false

        // Act
        val result = deleteDiscountUseCase(discountId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelDeleteException)

        coVerifySequence {
            discountsRepository.exists(discountId)
            discountsRepository.delete(discountId)
        }
    }
}