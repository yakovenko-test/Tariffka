package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.SupportTicket
import kotlinx.coroutines.flow.Flow

interface SupportTicketRepository {
    suspend fun create(supportTicket: SupportTicket)

    fun readById(supportTicketId: Int): Flow<SupportTicket?>
    fun readByUserId(userId: Int): Flow<List<SupportTicket>>
    fun readAll(): Flow<List<SupportTicket>>

    suspend fun update(supportTicket: SupportTicket)

    suspend fun deleteById(supportTicketId: Int)
}
