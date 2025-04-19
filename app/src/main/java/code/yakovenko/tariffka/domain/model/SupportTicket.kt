package code.yakovenko.tariffka.domain.model

import code.yakovenko.tariffka.domain.type.TicketStatus
import code.yakovenko.tariffka.common.validation.LocalDateTimeValidator
import code.yakovenko.tariffka.common.validation.StringFieldValidator
import code.yakovenko.tariffka.common.validation.validate
import kotlinx.datetime.LocalDateTime
import java.util.UUID

data class SupportTicket(
    val id: UUID = UUID.randomUUID(),
    val reporterId: UUID,
    val assigneeId: UUID?,
    val title: String,
    val description: String,
    val createdAt: LocalDateTime,
    val status: TicketStatus,
) {
    init {
        validate(title, "Title", StringFieldValidator)
        validate(description, "Description", StringFieldValidator)
        validate(createdAt, "CreatedAt", LocalDateTimeValidator)
    }
}
