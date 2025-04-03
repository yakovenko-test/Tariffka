package code.yakovenko.tariffka.domain.model

import code.yakovenko.tariffka.data.local.entity.SupportTicketEntity
import code.yakovenko.tariffka.domain.model.utils.TicketStatus
import java.time.LocalDateTime

data class SupportTicket(
    val id: Long,
    val title: String,
    val description: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val status: TicketStatus,
    val reporter: User,
    val assignee: User?,
) {
    init {
        require(createdAt <= LocalDateTime.now())
        require(updatedAt <= LocalDateTime.now())
        require(createdAt <= updatedAt)
    }
}

fun SupportTicket.toData() = SupportTicketEntity(
    id,
    title,
    description,
    createdAt,
    updatedAt,
    status,
    reporter.id,
    assignee?.id
)
