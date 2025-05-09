package code.yakovenko.data.mapping.support_ticket

import code.yakovenko.tariffka.core.Mapper
import code.yakovenko.tariffka.data.model.SupportTicketEntity
import code.yakovenko.tariffka.data.table.UsersTable
import code.yakovenko.tariffka.domain.model.SupportTicket
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction

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
