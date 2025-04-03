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
    1,
    "operator",
    URL("operator"),
    "operator",
    "operator",
    2025,
    5.0
)

val MOCKED_OPTION = Option(
    1,
    "option",
    1000,
    "option",
    MOCKED_OPERATOR
)

val MOCKED_TARIFF = Tariff(
    1,
    "tariff",
    2000,
    5.0,
    200,
    200,
    MOCKED_OPERATOR
)

val MOCKED_USER = User(
    1,
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
    MOCKED_OPERATOR,
    MOCKED_TARIFF,
    mutableListOf(MOCKED_OPTION)
)

val MOCKED_SUPPORT_TICKET = SupportTicket(
    1,
    "support_ticket",
    "support_ticket",
    LocalDateTime.now(),
    LocalDateTime.now(),
    MOCKED_USER,
    null,
    TicketStatus.OPEN
)

val MOCKED_TARIFF_FEEDBACK = TariffFeedback(
    1,
    "tariff_feedback",
    5,
    LocalDateTime.now(),
    MOCKED_TARIFF,
    MOCKED_USER
)

val MOCKED_TARIFF_DISCOUNT = TariffDiscount(
    1,
    LocalDateTime.now(),
    LocalDateTime.now(),
    2000,
    MOCKED_TARIFF
)
