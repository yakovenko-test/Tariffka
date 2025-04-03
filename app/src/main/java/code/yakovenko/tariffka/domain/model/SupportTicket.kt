package code.yakovenko.tariffka.domain.model

import code.yakovenko.tariffka.domain.model.utils.TicketStatus
import java.time.LocalDateTime

data class SupportTicket(
    val id: Long,
    val title: String,
    val description: String,
    val createdAt: LocalDateTime,
    var updatedAt: LocalDateTime,
    val author: User,
    var assignedTo: User?,
    var status: TicketStatus,
)
