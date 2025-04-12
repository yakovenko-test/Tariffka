package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.SupportTicket
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
interface SupportTicketRepository {
    suspend fun create(supportTicket: SupportTicket)

    fun readById(supportTicketId: Uuid): Flow<SupportTicket?>
    fun readByUserId(userId: Uuid): Flow<List<SupportTicket>>
    fun readAll(): Flow<List<SupportTicket>>

    suspend fun update(supportTicket: SupportTicket)

    suspend fun deleteById(supportTicketId: Uuid)
}
