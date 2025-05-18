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
import test.yakovenko.domain.model.Discount
import test.yakovenko.domain.model.TestDiscount
import test.yakovenko.domain.repository.DiscountsRepository
import test.yakovenko.domain.repository.OperatorsRepository
import java.util.UUID

class FindDiscountsByOperatorUseCaseTest {
    private lateinit var findDiscountsByOperatorUseCase: FindDiscountsByOperatorUseCase

    private val discountsRepository: DiscountsRepository = mockk()
    private val operatorsRepository: OperatorsRepository = mockk()

    private val operatorId = UUID.randomUUID()

    @BeforeEach
    fun setUp() {
        findDiscountsByOperatorUseCase = FindDiscountsByOperatorUseCase(
            discountsRepository, operatorsRepository
        )
    }

    @Test
    fun `should find discounts for existing operator`() = runTest {
        // Arrange
        val discounts = listOf(
            TestDiscount.create(operatorId = operatorId),
            TestDiscount.create(operatorId = operatorId)
        )

        coEvery { operatorsRepository.exists(operatorId) } returns true
        coEvery { discountsRepository.findByOperator(operatorId) } returns discounts

        // Act
        val result = findDiscountsByOperatorUseCase(operatorId)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(discounts, result.getOrNull())

        coVerifySequence {
            operatorsRepository.exists(operatorId)
            discountsRepository.findByOperator(operatorId)
        }
    }

    @Test
    fun `should return empty collection when operator has no discounts`() = runTest {
        // Arrange
        coEvery { operatorsRepository.exists(operatorId) } returns true
        coEvery { discountsRepository.findByOperator(operatorId) } returns emptyList<Discount>()

        // Act
        val result = findDiscountsByOperatorUseCase(operatorId)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(emptyList<Discount>(), result.getOrNull())

        coVerifySequence {
            operatorsRepository.exists(operatorId)
            discountsRepository.findByOperator(operatorId)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when operator does not exist`() = runTest {
        // Arrange
        coEvery { operatorsRepository.exists(operatorId) } returns false

        // Act
        val result = findDiscountsByOperatorUseCase(operatorId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerify(exactly = 1) { operatorsRepository.exists(operatorId) }
        coVerify(exactly = 0) { discountsRepository.findByOperator(any()) }
    }
}