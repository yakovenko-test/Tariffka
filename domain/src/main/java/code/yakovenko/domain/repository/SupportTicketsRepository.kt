package code.yakovenko.domain.repository

import code.yakovenko.domain.model.SupportTicket
import java.util.UUID

interface SupportTicketsRepository {
    fun create(supportTicket: SupportTicket): SupportTicket

    fun readById(supportTicketId: UUID): SupportTicket?
    fun readByUserId(userId: UUID): Collection<SupportTicket>
    fun readAll(): Collection<SupportTicket>

    fun update(supportTicket: SupportTicket): SupportTicket?

    fun deleteById(supportTicketId: UUID)

    fun containsId(supportTicketId: UUID): Boolean
    fun notContainsId(supportTicketId: UUID) = !containsId(supportTicketId)
}
