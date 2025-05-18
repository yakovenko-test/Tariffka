package test.yakovenko.domain.usecase.discount

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
import test.yakovenko.domain.exception.ModelUpdateException
import test.yakovenko.domain.model.TestDiscount
import test.yakovenko.domain.repository.DiscountsRepository
import test.yakovenko.domain.repository.OperatorsRepository
import java.util.UUID

class UpdateDiscountUseCaseTest {
    private lateinit var updateDiscountUseCase: UpdateDiscountUseCase

    private val discountsRepository: DiscountsRepository = mockk()
    private val operatorsRepository: OperatorsRepository = mockk()

    private val discount = TestDiscount.create(operatorId = UUID.randomUUID())

    @BeforeEach
    fun setUp() {
        updateDiscountUseCase = UpdateDiscountUseCase(discountsRepository, operatorsRepository)
    }

    @Test
    fun `should update discount successfully`() = runTest {
        // Arrange
        coEvery { discountsRepository.exists(discount.id) } returns true
        coEvery { operatorsRepository.exists(discount.operatorId) } returns true
        coEvery { discountsRepository.update(discount) } returns discount

        // Act
        val result = updateDiscountUseCase(discount)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(discount, result.getOrNull())

        coVerifySequence {
            discountsRepository.exists(discount.id)
            operatorsRepository.exists(discount.operatorId)
            discountsRepository.update(discount)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when discount does not exist`() = runTest {
        // Arrange
        coEvery { discountsRepository.exists(discount.id) } returns false

        // Act
        val result = updateDiscountUseCase(discount)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerify(exactly = 1) { discountsRepository.exists(discount.id) }
        coVerify(exactly = 0) { operatorsRepository.exists(any()) }
        coVerify(exactly = 0) { discountsRepository.update(any()) }
    }

    @Test
    fun `should fail with ModelNotFoundException when operator does not exist`() = runTest {
        // Arrange
        coEvery { discountsRepository.exists(discount.id) } returns true
        coEvery { operatorsRepository.exists(discount.operatorId) } returns false

        // Act
        val result = updateDiscountUseCase(discount)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerifySequence {
            discountsRepository.exists(discount.id)
            operatorsRepository.exists(discount.operatorId)
        }
        coVerify(exactly = 0) { discountsRepository.update(any()) }
    }

    @Test
    fun `should fail with ModelUpdateException when update returns null`() = runTest {
        // Arrange
        coEvery { discountsRepository.exists(discount.id) } returns true
        coEvery { operatorsRepository.exists(discount.operatorId) } returns true
        coEvery { discountsRepository.update(discount) } returns null

        // Act
        val result = updateDiscountUseCase(discount)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelUpdateException)

        coVerifySequence {
            discountsRepository.exists(discount.id)
            operatorsRepository.exists(discount.operatorId)
            discountsRepository.update(discount)
        }
    }
}