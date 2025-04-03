package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.data.local.dao.SupportTicketDao
import code.yakovenko.tariffka.data.local.entity.toDomain
import code.yakovenko.tariffka.domain.model.SupportTicket
import code.yakovenko.tariffka.domain.model.toData
import code.yakovenko.tariffka.domain.model.utils.IdType
import code.yakovenko.tariffka.domain.repository.SupportTicketRepository
import javax.inject.Inject

class SupportTicketRepositoryImpl @Inject constructor(
    private val supportTicketDao: SupportTicketDao
) : SupportTicketRepository {
    override suspend fun create(supportTicket: SupportTicket) {
        supportTicketDao.insert(supportTicket.toData())
    }

    override suspend fun readById(supportTicketId: IdType): SupportTicket? {
        return supportTicketDao.selectById(supportTicketId)?.toDomain()
    }

    override suspend fun readAll(): List<SupportTicket> {
        return supportTicketDao.selectAll().map { it.toDomain() }
    }

    override suspend fun update(supportTicket: SupportTicket) {
        supportTicketDao.update(supportTicket.toData())
    }

    override suspend fun deleteById(supportTicketId: IdType) {
        supportTicketDao.deleteById(supportTicketId)
    }
}
