package code.yakovenko.data.repository

import code.yakovenko.tariffka.core.transformAll
import code.yakovenko.tariffka.data.mapping.support_ticket.SupportTicketEntityToDomainMapper
import code.yakovenko.tariffka.data.mapping.support_ticket.SupportTicketToEntityMapper
import code.yakovenko.tariffka.data.model.SupportTicketEntity
import code.yakovenko.tariffka.data.table.SupportTicketsTable
import code.yakovenko.tariffka.data.table.UsersTable
import code.yakovenko.tariffka.domain.model.SupportTicket
import code.yakovenko.tariffka.domain.repository.SupportTicketRepository
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID
import javax.inject.Inject

class DatabaseSupportTicketRepository @Inject constructor(
    private val supportTicketToEntityMapper: SupportTicketToEntityMapper,
    private val supportTicketEntityToDomainMapper: SupportTicketEntityToDomainMapper
) : SupportTicketRepository {
    override fun create(supportTicket: SupportTicket): SupportTicket = transaction {
        supportTicketEntityToDomainMapper.transform(
            supportTicketToEntityMapper.transform(supportTicket)
        )
    }

    override fun readById(supportTicketId: UUID): SupportTicket? = transaction {
        SupportTicketEntity.findById(supportTicketId)?.let { supportTicket ->
            supportTicketEntityToDomainMapper.transform(supportTicket)
        }
    }

    override fun readByUserId(userId: UUID): Collection<SupportTicket> = transaction {
        supportTicketEntityToDomainMapper.transformAll(
            SupportTicketEntity.find { SupportTicketsTable.reporterId eq userId }.toList()
        )
    }

    override fun readAll(): Collection<SupportTicket> = transaction {
        supportTicketEntityToDomainMapper.transformAll(
            SupportTicketEntity.all().toList()
        )
    }

    override fun update(supportTicket: SupportTicket): SupportTicket? = transaction {
        SupportTicketEntity.findByIdAndUpdate(supportTicket.id) {
            it.reporterId = EntityID(supportTicket.reporterId, UsersTable)
            it.assigneeId = supportTicket.assigneeId?.let {
                assigneeId -> EntityID(assigneeId, UsersTable)
            }
            it.title = supportTicket.title
            it.description = supportTicket.description
            it.createdAt = supportTicket.createdAt
            it.updatedAt = supportTicket.updatedAt
            it.status = supportTicket.status
        }?.let { supportTicketEntity ->
            supportTicketEntityToDomainMapper.transform(supportTicketEntity)
        }
    }

    override fun deleteById(supportTicketId: UUID): Unit = transaction {
        SupportTicketEntity.findById(supportTicketId)?.delete()
    }

    override fun containsId(supportTicketId: UUID): Boolean = transaction {
        SupportTicketEntity.findById(supportTicketId) != null
    }
}
