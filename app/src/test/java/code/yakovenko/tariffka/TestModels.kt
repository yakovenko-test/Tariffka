package code.yakovenko.tariffka

import code.yakovenko.tariffka.domain.model.Discount
import code.yakovenko.tariffka.domain.model.Operator
import code.yakovenko.tariffka.domain.model.Service
import code.yakovenko.tariffka.domain.model.SupportTicket
import code.yakovenko.tariffka.domain.model.Tariff
import code.yakovenko.tariffka.domain.model.TariffFeedback
import code.yakovenko.tariffka.domain.model.User
import code.yakovenko.tariffka.domain.type.Currency
import code.yakovenko.tariffka.domain.type.TicketStatus
import code.yakovenko.tariffka.domain.type.UserGender
import code.yakovenko.tariffka.domain.type.UserRole
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime

internal val testOperator = Operator(
    name = "TestOperator",
    countryCode = CountryCode.RUS,
    url = null,
    yearOfFoundation = null
)

internal val testTariff = Tariff(
    operatorId = testOperator.id,
    name = "Test Tariff",
    description = "Test Description",
    cost = 1000u,
    currency = Currency.RUB,
    minutesCount = null,
    gigabytesCount = null,
    averageEstimation = null
)

internal val testService = Service(
    operatorId = testOperator.id,
    name = "Test Service",
    description = "Test Description",
    cost = 1000u,
    currency = Currency.RUB
)

internal val testUser = User(
    operatorId = testOperator.id,
    tariffId = testTariff.id,
    name = "Test Name",
    surname = "Test Surname",
    patronymic = null,
    username = "test_username",
    phoneNumber = "100",
    email = null,
    gender = UserGender.NOT_SPECIFIED,
    birthDate = LocalDate.fromEpochDays(0),
    password = "Test Passw0rd",
    role = UserRole.USER
)

internal val testTariffFeedback = TariffFeedback(
    authorId = testUser.id,
    tariffId = testTariff.id,
    description = null,
    estimation = 0u,
    publishedAt = LocalDate.fromEpochDays(0)
        .atStartOfDayIn(TimeZone.UTC)
        .toLocalDateTime(TimeZone.UTC)
)

internal val testSupportTicket = SupportTicket(
    reporterId = testUser.id,
    assigneeId = null,
    title = "Test Title",
    description = "Test Description",
    createdAt = LocalDate.fromEpochDays(0)
        .atStartOfDayIn(TimeZone.UTC)
        .toLocalDateTime(TimeZone.UTC),
    updatedAt = null,
    status = TicketStatus.OPEN
)

internal val testDiscount = Discount(
    operatorId = testOperator.id,
    title = "Test Title",
    description = "Test Description",
    activeFrom = LocalDate.fromEpochDays(0)
        .atStartOfDayIn(TimeZone.UTC)
        .toLocalDateTime(TimeZone.UTC),
    activeUntil = null
)
