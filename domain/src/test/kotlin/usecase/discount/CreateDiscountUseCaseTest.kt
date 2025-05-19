package usecase.discount

import exception.ModelCreateException
import exception.ModelDuplicateException
import exception.ModelNotFoundException
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
import repository.OperatorsRepository
import java.util.*

class CreateDiscountUseCaseTest {
    private lateinit var createDiscountUseCase: CreateDiscountUseCase

    private val discountsRepository: DiscountsRepository = mockk()
    private val operatorsRepository: OperatorsRepository = mockk()

    private val discount = TestDiscount.create(operatorId = UUID.randomUUID())

    @BeforeEach
    fun setUp() {
        createDiscountUseCase = CreateDiscountUseCase(discountsRepository, operatorsRepository)
    }

    @Test
    fun `should create discount successfully`() = runTest {
        // Arrange
        coEvery { discountsRepository.exists(discount.id) } returns false
        coEvery { operatorsRepository.exists(discount.operatorId) } returns true
        coEvery { discountsRepository.create(discount) } returns discount

        // Act
        val result = createDiscountUseCase(discount)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(discount, result.getOrNull())

        coVerifySequence {
            discountsRepository.exists(discount.id)
            operatorsRepository.exists(discount.operatorId)
            discountsRepository.create(discount)
        }
    }

    @Test
    fun `should fail with ModelDuplicateException when discount already exists`() = runTest {
        // Arrange
        coEvery { discountsRepository.exists(discount.id) } returns true

        // Act
        val result = createDiscountUseCase(discount)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelDuplicateException)

        coVerify(exactly = 1) { discountsRepository.exists(discount.id) }
        coVerify(exactly = 0) { operatorsRepository.exists(any()) }
        coVerify(exactly = 0) { discountsRepository.create(any()) }
    }

    @Test
    fun `should fail with ModelNotFoundException when operator does not exist`() = runTest {
        // Arrange
        coEvery { discountsRepository.exists(discount.id) } returns false
        coEvery { operatorsRepository.exists(discount.operatorId) } returns false

        // Act
        val result = createDiscountUseCase(discount)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerifySequence {
            discountsRepository.exists(discount.id)
            operatorsRepository.exists(discount.operatorId)
        }
        coVerify(exactly = 0) { discountsRepository.create(any()) }
    }

    @Test
    fun `should fail with ModelCreateException when create returns null`() = runTest {
        // Arrange
        coEvery { discountsRepository.exists(discount.id) } returns false
        coEvery { operatorsRepository.exists(discount.operatorId) } returns true
        coEvery { discountsRepository.create(discount) } returns null

        // Act
        val result = createDiscountUseCase(discount)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelCreateException)

        coVerifySequence {
            discountsRepository.exists(discount.id)
            operatorsRepository.exists(discount.operatorId)
            discountsRepository.create(discount)
        }
    }
}