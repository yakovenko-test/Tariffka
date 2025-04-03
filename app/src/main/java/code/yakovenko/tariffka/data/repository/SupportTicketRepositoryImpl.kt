package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.data.local.dao.SupportTicketDao
import code.yakovenko.tariffka.data.local.entity.toDomain
import code.yakovenko.tariffka.domain.model.SupportTicket
import code.yakovenko.tariffka.domain.repository.SupportTicketRepository
import javax.inject.Inject

class SupportTicketRepositoryImpl @Inject constructor(
    private val supportTicketDao: SupportTicketDao
): SupportTicketRepository {
    override suspend fun getAll(): List<SupportTicket> {
        return supportTicketDao.getAll().map { it.toDomain() }
    }
}
