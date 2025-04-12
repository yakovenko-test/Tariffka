package code.yakovenko.tariffka.domain.usecase.create

import code.yakovenko.tariffka.core.enums.CountryCode
import code.yakovenko.tariffka.core.enums.Currency
import code.yakovenko.tariffka.core.enums.UserGender
import code.yakovenko.tariffka.core.enums.UserRole
import code.yakovenko.tariffka.domain.exception.OperatorNotFoundException
import code.yakovenko.tariffka.domain.exception.TariffNotFoundException
import code.yakovenko.tariffka.domain.model.Operator
import code.yakovenko.tariffka.domain.model.Tariff
import code.yakovenko.tariffka.domain.model.User
import code.yakovenko.tariffka.mock.OperatorRepositoryMock
import code.yakovenko.tariffka.mock.TariffRepositoryMock
import code.yakovenko.tariffka.mock.UserRepositoryMock
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class CreateUserUseCaseTest {
    private lateinit var userRepository: UserRepositoryMock
    private lateinit var operatorRepository: OperatorRepositoryMock
    private lateinit var tariffRepository: TariffRepositoryMock
    private lateinit var createUserUseCase: CreateUserUseCase

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
        averageEstimation = 0.0
    )

    private val testUser = User(
        operatorId = testOperator.id,
        tariffId = testTariff.id,
        name = "John",
        surname = "Doe",
        patronymic = null,
        username = "john",
        phoneNumber = "123456789",
        email = "test@test.com",
        gender = UserGender.MALE,
        role = UserRole.USER,
        birthDate = LocalDate.now(),
        password = "Password_J0hn"
    )

    @Before
    fun setup() {
        userRepository = UserRepositoryMock()
        operatorRepository = OperatorRepositoryMock()
        tariffRepository = TariffRepositoryMock()
        createUserUseCase = CreateUserUseCase(
            userRepository,
            operatorRepository,
            tariffRepository
        )
    }

    @Test
    fun `should create user with all references`() = runTest {
        operatorRepository.create(testOperator)
        tariffRepository.create(testTariff)

        createUserUseCase(testUser)

        val createdUser = userRepository.readById(testUser.id).first()
        assertEquals(testUser, createdUser)
    }

    @Test
    fun `should throw when operator not found`() = runTest {
        tariffRepository.create(testTariff)

        val exception = assertFailsWith<OperatorNotFoundException> {
            createUserUseCase(testUser)
        }
        assertEquals("Operator with id ${testUser.operatorId} not found", exception.message)

        assertNull(userRepository.readById(testUser.id).first())
    }

    @Test
    fun `should throw when tariff not found`() = runTest {
        operatorRepository.create(testOperator)

        val exception = assertFailsWith<TariffNotFoundException> {
            createUserUseCase(testUser)
        }
        assertEquals("Tariff with id ${testUser.tariffId} not found", exception.message)

        assertNull(userRepository.readById(testUser.id).first())
    }
}