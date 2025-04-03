package code.yakovenko.tariffka.domain.model

import code.yakovenko.tariffka.data.local.entity.SupportTicketEntity
import code.yakovenko.tariffka.domain.model.utils.IdType
import code.yakovenko.tariffka.domain.model.utils.TicketStatus
import java.time.LocalDateTime

data class SupportTicket(
    val id: IdType,
    val title: String,
    val description: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val status: TicketStatus,
    val reporterId: IdType,
    val assigneeId: IdType?,
)

fun SupportTicket.toData() = SupportTicketEntity(id.id)
