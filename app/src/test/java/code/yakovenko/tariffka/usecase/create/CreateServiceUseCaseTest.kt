package code.yakovenko.tariffka.usecase.create

import code.yakovenko.tariffka.domain.type.Currency
import code.yakovenko.tariffka.domain.model.Operator
import code.yakovenko.tariffka.domain.model.Service
import code.yakovenko.tariffka.domain.usecase.service.CreateServiceUseCase
import kotlinx.coroutines.flow.first
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class CreateServiceUseCaseTest {
//    private lateinit var serviceRepository: ServiceRepositoryMock
//    private lateinit var operatorRepository: OperatorRepositoryMock
//    private lateinit var createServiceUseCase: CreateServiceUseCase
//
//    private val testOperator = Operator(
//        name = "Test Operator",
//        description = "Test",
//        countryCode = CountryCode.RUS,
//        url = "http://test.com",
//        yearOfFoundation = 2000u
//    )
//
//    private val testService = Service(
//        operatorId = testOperator.id,
//        name = "Internet",
//        description = "100 Mbps",
//        cost = 500u,
//        currency = Currency.RUB
//    )
//
//    @Before
//    fun setup() {
//        serviceRepository = ServiceRepositoryMock()
//        operatorRepository = OperatorRepositoryMock()
//        createServiceUseCase = CreateServiceUseCase(serviceRepository, operatorRepository)
//    }
//
//    @Test
//    fun `should create service when operator exists`() = runTest {
//        operatorRepository.create(testOperator)
//
//        createServiceUseCase(testService)
//
//        val createdService = serviceRepository.readById(testService.id).first()
//        assertEquals(testService, createdService)
//    }
//
//    @Test
//    fun `should throw when operator not found`() = runTest {
//        val exception = assertFailsWith<OperatorNotFoundException> {
//            createServiceUseCase(testService)
//        }
//        assertEquals("Operator with id ${testService.operatorId} not found", exception.message)
//
//        assertNull(serviceRepository.readById(testService.id).first())
//    }
}
