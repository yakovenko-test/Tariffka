package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.data.local.dao.SupportTicketDao
import code.yakovenko.tariffka.data.mapping.SupportTicketMapper
import code.yakovenko.tariffka.domain.model.SupportTicket
import code.yakovenko.tariffka.domain.repository.SupportTicketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SupportTicketRepositoryImpl(
    private val supportTicketDao: SupportTicketDao
) : SupportTicketRepository {
    override suspend fun create(supportTicket: SupportTicket) {
        supportTicketDao.insertSupportTicket(SupportTicketMapper.toData(supportTicket))
    }

    override fun readById(supportTicketId: Int): Flow<SupportTicket?> {
        return supportTicketDao.selectSupportTicketById(supportTicketId).map { entity ->
            entity?.let { SupportTicketMapper.toDomain(it) }
        }
    }

    override fun readByUserId(userId: Int): Flow<List<SupportTicket>> {
        return supportTicketDao.selectSupportTicketsByUserId(userId).map { entities ->
            entities.map { SupportTicketMapper.toDomain(it) }
        }
    }

    override fun readAll(): Flow<List<SupportTicket>> {
        return supportTicketDao.selectAllSupportTickets().map { entities ->
            entities.map { SupportTicketMapper.toDomain(it) }
        }
    }

    override suspend fun update(supportTicket: SupportTicket) {
        supportTicketDao.updateSupportTicket(SupportTicketMapper.toData(supportTicket))
    }

    override suspend fun deleteById(supportTicketId: Int) {
        supportTicketDao.deleteSupportTicketById(supportTicketId)
    }
}
