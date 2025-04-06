package code.yakovenko.tariffka.domain.model

import code.yakovenko.tariffka.core.utils.TicketStatus
import java.time.LocalDateTime

data class SupportTicket(
    val id: Int,
    val reporterId: Int,
    val assigneeId: Int?,
    val title: String,
    val description: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val status: TicketStatus,
) {
    init {
        require(title.isNotBlank())
        require(description.isNotBlank())
        require(createdAt <= LocalDateTime.now())
        require(updatedAt <= LocalDateTime.now())
        require(createdAt <= updatedAt)
    }
}
