package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.SupportTicket

interface SupportTicketRepository {
    suspend fun getAll(): List<SupportTicket>
}
