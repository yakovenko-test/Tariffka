package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.data.local.dao.SupportTicketDao
import code.yakovenko.tariffka.data.mapping.SupportTicketMapper
import code.yakovenko.tariffka.domain.model.SupportTicket
import code.yakovenko.tariffka.domain.repository.SupportTicketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SupportTicketRepositoryImpl @Inject constructor(
    private val supportTicketDao: SupportTicketDao
) : SupportTicketRepository {
    override suspend fun create(supportTicket: SupportTicket) {
        supportTicketDao.insert(SupportTicketMapper.toData(supportTicket))
    }

    override suspend fun readById(supportTicketId: Int): SupportTicket? {
        return supportTicketDao.selectById(supportTicketId)?.let {
            SupportTicketMapper.toDomain(it)
        }
    }

    override suspend fun readAll(): Flow<List<SupportTicket>> {
        return supportTicketDao.selectAll().map { entities ->
            entities.map { SupportTicketMapper.toDomain(it) }
        }
    }

    override suspend fun update(supportTicket: SupportTicket) {
        supportTicketDao.update(SupportTicketMapper.toData(supportTicket))
    }

    override suspend fun deleteById(supportTicketId: Int) {
        supportTicketDao.deleteById(supportTicketId)
    }
}
