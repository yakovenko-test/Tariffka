package code.yakovenko.tariffka.domain.model.utils

import code.yakovenko.tariffka.domain.model.utils.TicketStatus.CLOSED
import code.yakovenko.tariffka.domain.model.utils.TicketStatus.IN_PROGRESS
import code.yakovenko.tariffka.domain.model.utils.TicketStatus.OPEN
import code.yakovenko.tariffka.domain.model.utils.TicketStatus.REJECTED
import code.yakovenko.tariffka.domain.model.utils.TicketStatus.RESOLVED


/**
 * @property OPEN Обращение создано, но еще не обработано.
 * @property IN_PROGRESS Обращение находится в процессе обработки.
 * @property RESOLVED Обращение успешно решено.
 * @property CLOSED Обращение закрыто.
 * @property REJECTED Обращение отклонено.
 */
enum class TicketStatus {
    OPEN,
    IN_PROGRESS,
    RESOLVED,
    CLOSED,
    REJECTED,
}
