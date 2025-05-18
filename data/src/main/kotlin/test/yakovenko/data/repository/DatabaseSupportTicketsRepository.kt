package test.yakovenko.data.repository

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction
import test.yakovenko.tariffka.core.transformAll
import test.yakovenko.tariffka.data.mapping.support_ticket.SupportTicketEntityToDomainMapper
import test.yakovenko.tariffka.data.mapping.support_ticket.SupportTicketToEntityMapper
import test.yakovenko.tariffka.data.model.SupportTicketEntity
import test.yakovenko.tariffka.data.table.SupportTicketsTable
import test.yakovenko.tariffka.data.table.UsersTable
import test.yakovenko.tariffka.domain.model.SupportTicket
import test.yakovenko.tariffka.domain.repository.SupportTicketRepository
import java.util.UUID
import javax.inject.Inject

class DatabaseSupportTicketsRepository @Inject constructor(
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
