package code.yakovenko.tariffka.domain.usecase.create

import code.yakovenko.tariffka.core.enums.CountryCode
import code.yakovenko.tariffka.core.enums.Currency
import code.yakovenko.tariffka.domain.exception.OperatorNotFoundException
import code.yakovenko.tariffka.domain.model.Operator
import code.yakovenko.tariffka.domain.model.Tariff
import code.yakovenko.tariffka.mock.OperatorRepositoryMock
import code.yakovenko.tariffka.mock.TariffRepositoryMock
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class CreateTariffUseCaseTest {
    private lateinit var tariffRepository: TariffRepositoryMock
    private lateinit var operatorRepository: OperatorRepositoryMock
    private lateinit var createTariffUseCase: CreateTariffUseCase

    private val testOperator = Operator(
        name = "Test Operator",
        description = "Test",
        countryCode = CountryCode.RU,
        url = "http://test.com",
        yearOfFoundation = 2000u
    )

    private val testTariff = Tariff(
        operatorId = testOperator.id,
        name = "Premium",
        description = "Test",
        cost = 1000u,
        currency = Currency.RUB,
        minutesCount = 1000u,
        gigabytesCount = 30u,
        averageEstimation = 0.0,
    )

    @Before
    fun setup() {
        tariffRepository = TariffRepositoryMock()
        operatorRepository = OperatorRepositoryMock()
        createTariffUseCase = CreateTariffUseCase(tariffRepository, operatorRepository)
    }

    @Test
    fun `should create tariff when operator exists`() = runTest {
        operatorRepository.create(testOperator)

        createTariffUseCase(testTariff)

        val createdTariff = tariffRepository.readById(testTariff.id).first()
        assertEquals(testTariff, createdTariff)
    }

    @Test
    fun `should throw when operator not found`() = runTest {
        val exception = assertFailsWith<OperatorNotFoundException> {
            createTariffUseCase(testTariff)
        }

        assertEquals("Operator with id ${testTariff.operatorId} not found", exception.message)
        assertNull(tariffRepository.readById(testTariff.id).first())
    }
}