package model

import extension.now
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import model.type.TicketStatus
import java.util.*

object TestSupportTicket {
    fun create(
        id: UUID = UUID.randomUUID(),
        reporterId: UUID,
        assigneeId: UUID? = null,
        title: String = "Test Ticket Title",
        description: String = "This is a test description for the support ticket.",
        status: TicketStatus = TicketStatus.OPEN,
        createdAt: LocalDateTime = LocalDateTime.now(TimeZone.UTC),
        updatedAt: LocalDateTime = LocalDateTime.now(TimeZone.UTC),
    ): SupportTicket {
        return SupportTicket(
            id = id,
            reporterId = reporterId,
            assigneeId = assigneeId,
            title = title,
            description = description,
            status = status,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }
}
