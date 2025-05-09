package code.yakovenko.domain.model

import code.yakovenko.domain.type.TicketStatus
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
    }
}
