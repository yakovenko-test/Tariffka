package code.yakovenko.tariffka.domain.usecase.create

import code.yakovenko.tariffka.core.enums.CountryCode
import code.yakovenko.tariffka.domain.exception.OperatorNotFoundException
import code.yakovenko.tariffka.domain.model.Discount
import code.yakovenko.tariffka.domain.model.Operator
import code.yakovenko.tariffka.mock.DiscountRepositoryMock
import code.yakovenko.tariffka.mock.OperatorRepositoryMock
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class CreateDiscountUseCaseTest {
    private lateinit var discountRepository: DiscountRepositoryMock
    private lateinit var operatorRepository: OperatorRepositoryMock
    private lateinit var createDiscountUseCase: CreateDiscountUseCase

    private val testOperator = Operator(
        name = "Test Operator",
        description = "Test",
        countryCode = CountryCode.RU,
        url = "http://test.com",
        yearOfFoundation = 2000u
    )

    private val testDiscount = Discount(
        operatorId = testOperator.id,
        title = "Summer Sale",
        description = "50% off",
        activeFrom = LocalDateTime.now(),
        activeUntil = LocalDateTime.now().plusDays(7)
    )

    @Before
    fun setup() {
        discountRepository = DiscountRepositoryMock()
        operatorRepository = OperatorRepositoryMock()
        createDiscountUseCase = CreateDiscountUseCase(discountRepository, operatorRepository)
    }

    @Test
    fun `should create discount when operator exists`() = runTest {
        operatorRepository.create(testOperator)

        createDiscountUseCase(testDiscount)

        val createdDiscount = discountRepository.readById(testDiscount.id).first()
        assertEquals(testDiscount, createdDiscount)
    }

    @Test
    fun `should throw when operator not found`() = runTest {
        val exception = assertFailsWith<OperatorNotFoundException> {
            createDiscountUseCase(testDiscount)
        }
        assertEquals("Operator with id ${testDiscount.operatorId} not found", exception.message)

        assertNull(discountRepository.readById(testDiscount.id).first())
    }
}
