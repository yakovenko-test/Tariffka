package code.yakovenko.tariffka.domain.type

import code.yakovenko.tariffka.domain.type.TicketStatus.IN_PROGRESS
import code.yakovenko.tariffka.domain.type.TicketStatus.OPEN
import code.yakovenko.tariffka.domain.type.TicketStatus.REJECTED
import code.yakovenko.tariffka.domain.type.TicketStatus.RESOLVED

/**
 * @property OPEN Обращение создано, но еще не обработано.
 * @property IN_PROGRESS Обращение находится в процессе обработки.
 * @property RESOLVED Обращение успешно решено.
 * @property REJECTED Обращение отклонено.
 */
enum class TicketStatus {
    OPEN,
    IN_PROGRESS,
    RESOLVED,
    REJECTED;

    companion object {
        val MAX_NAME_LENGTH = entries.maxOf { it.name.length }
    }
}
