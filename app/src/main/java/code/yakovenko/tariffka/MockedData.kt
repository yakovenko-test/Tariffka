package code.yakovenko.tariffka

import code.yakovenko.tariffka.domain.model.Operator
import code.yakovenko.tariffka.domain.model.Option
import code.yakovenko.tariffka.domain.model.SupportTicket
import code.yakovenko.tariffka.domain.model.Tariff
import code.yakovenko.tariffka.domain.model.TariffDiscount
import code.yakovenko.tariffka.domain.model.TariffFeedback
import code.yakovenko.tariffka.domain.model.User
import code.yakovenko.tariffka.domain.model.utils.TicketStatus
import code.yakovenko.tariffka.domain.model.utils.UserGender
import code.yakovenko.tariffka.domain.model.utils.UserRole
import java.net.URL
import java.time.LocalDateTime
import java.util.Date

val MOCKED_OPERATOR = Operator(
    IdType(1),
    "operator",
    "operator",
    "operator",
    "operator",
    2025,
    5.0
)

val MOCKED_OPTION = Option(
    IdType(1),
    "option",
    1000,
    "option",
    MOCKED_OPERATOR.id
)

val MOCKED_TARIFF = Tariff(
    IdType(1),
    "tariff",
    2000,
    5.0,
    200,
    200,
    MOCKED_OPERATOR.id
)

val MOCKED_USER = User(
    IdType(1),
    "user",
    "user",
    "user",
    "user",
    "user",
    "user",
    "user",
    Date(2025),
    UserGender.MALE,
    UserRole.USER,
    MOCKED_OPERATOR.id,
    MOCKED_TARIFF.id
)

val MOCKED_SUPPORT_TICKET = SupportTicket(
    IdType(1),
    "support_ticket",
    "support_ticket",
    LocalDateTime.now(),
    LocalDateTime.now(),
    TicketStatus.OPEN,
    MOCKED_USER.id,
    null,
)

val MOCKED_TARIFF_FEEDBACK = TariffFeedback(
    IdType(1),
    "tariff_feedback",
    5,
    LocalDateTime.now(),
    MOCKED_TARIFF.id,
    MOCKED_USER.id
)

val MOCKED_TARIFF_DISCOUNT = TariffDiscount(
    IdType(1),
    LocalDateTime.now(),
    LocalDateTime.now(),
    2000,
    MOCKED_TARIFF.id
)
