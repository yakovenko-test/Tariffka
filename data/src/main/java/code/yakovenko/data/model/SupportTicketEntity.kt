package code.yakovenko.data.model

import code.yakovenko.tariffka.data.table.SupportTicketsTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

class SupportTicketEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    var reporterId by SupportTicketsTable.reporterId
    var assigneeId by SupportTicketsTable.assigneeId
    var title by SupportTicketsTable.title
    var description by SupportTicketsTable.description
    var createdAt by SupportTicketsTable.createdAt
    var updatedAt by SupportTicketsTable.updatedAt
    var status by SupportTicketsTable.status

    companion object : UUIDEntityClass<SupportTicketEntity>(SupportTicketsTable)
}
