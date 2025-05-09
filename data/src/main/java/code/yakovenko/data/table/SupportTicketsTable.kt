package code.yakovenko.data.table

import code.yakovenko.tariffka.domain.type.TicketStatus
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime
import java.util.UUID

internal object SupportTicketsTable : IdTable<UUID>("support_tickets") {
    override val id = uuid("id").entityId()

    val reporterId = reference("reported_id", UsersTable)
    val assigneeId = optReference("assignee_id", UsersTable)
    val title = text("title")
    val description = text("description")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at").nullable()
    val status = enumerationByName<TicketStatus>("status", TicketStatus.MAX_NAME_LENGTH)
}
