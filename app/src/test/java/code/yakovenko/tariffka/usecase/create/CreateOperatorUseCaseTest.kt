package code.yakovenko.tariffka.usecase.create

import code.yakovenko.tariffka.domain.model.Operator
import code.yakovenko.tariffka.domain.usecase.operator.CreateOperatorUseCase
import kotlinx.coroutines.flow.first
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class CreateOperatorUseCaseTest {
//    private lateinit var operatorRepository: OperatorRepositoryMock
//    private lateinit var createOperatorUseCase: CreateOperatorUseCase
//
//    private val testOperator = Operator(
//        name = "Test Operator",
//        description = "Test",
//        countryCode = CountryCode.RUS,
//        url = "http://test.com",
//        yearOfFoundation = 2000u
//    )
//
//    @Before
//    fun setup() {
//        operatorRepository = OperatorRepositoryMock()
//        createOperatorUseCase = CreateOperatorUseCase(operatorRepository)
//    }
//
//    @Test
//    fun `should create operator successfully`() = runTest {
//        createOperatorUseCase(testOperator)
//
//        val createdOperator = operatorRepository.readById(testOperator.id).first()
//        assertEquals(testOperator, createdOperator)
//    }
}
