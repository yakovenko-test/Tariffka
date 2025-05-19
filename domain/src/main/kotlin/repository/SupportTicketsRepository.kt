package repository

import model.SupportTicket
import java.util.*

interface SupportTicketsRepository {
    suspend fun create(supportTicket: SupportTicket): SupportTicket?
    suspend fun read(supportTicketId: UUID): SupportTicket?
    suspend fun update(supportTicket: SupportTicket): SupportTicket?
    suspend fun delete(supportTicketId: UUID): Boolean

    suspend fun readAll(): Collection<SupportTicket>

    suspend fun findByReporter(userId: UUID): Collection<SupportTicket>
    suspend fun findByAssignee(userId: UUID): Collection<SupportTicket>

    suspend fun exists(supportTicketId: UUID): Boolean
}
