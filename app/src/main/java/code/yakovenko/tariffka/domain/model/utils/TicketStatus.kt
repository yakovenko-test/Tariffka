package code.yakovenko.tariffka.domain.model.utils

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
