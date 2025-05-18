package test.yakovenko.data.mapping.support_ticket

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction
import test.yakovenko.tariffka.core.Mapper
import test.yakovenko.tariffka.data.model.SupportTicketEntity
import test.yakovenko.tariffka.data.table.UsersTable
import test.yakovenko.tariffka.domain.model.SupportTicket

class SupportTicketToEntityMapper : Mapper<SupportTicket, SupportTicketEntity> {
    override fun transform(from: SupportTicket): SupportTicketEntity = transaction {
        SupportTicketEntity.new(from.id) {
            reporterId = EntityID(from.reporterId, UsersTable)
            assigneeId = from.assigneeId?.let { assigneeId -> EntityID(assigneeId, UsersTable) }
            title = from.title
            description = from.description
            createdAt = from.createdAt
            updatedAt = from.updatedAt
            status = from.status
        }
    }
}
