package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.SupportTicket
import code.yakovenko.tariffka.domain.model.utils.IdType

interface SupportTicketRepository {
    suspend fun create(supportTicket: SupportTicket)

    suspend fun readById(supportTicketId: IdType): SupportTicket?
    suspend fun readAll(): List<SupportTicket>

    suspend fun update(supportTicket: SupportTicket)

    suspend fun deleteById(supportTicketId: IdType)
}
