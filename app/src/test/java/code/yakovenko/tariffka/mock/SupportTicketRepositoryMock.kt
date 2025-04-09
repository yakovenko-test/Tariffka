package code.yakovenko.tariffka.mock

import code.yakovenko.tariffka.domain.model.SupportTicket
import code.yakovenko.tariffka.domain.repository.SupportTicketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

class SupportTicketRepositoryMock : SupportTicketRepository {
    private val data = mutableListOf<SupportTicket>()
    private val dataFlow = MutableStateFlow<List<SupportTicket>>(emptyList())

    override suspend fun create(supportTicket: SupportTicket) {
        data.add(supportTicket)
        dataFlow.value = data.toList()
    }

    override fun readById(supportTicketId: Int): Flow<SupportTicket?> {
        return dataFlow.map { supportTickets ->
            supportTickets.find { it.id == supportTicketId }
        }
    }

    override fun readByUserId(userId: Int): Flow<List<SupportTicket>> {
        return dataFlow.map { supportTickets ->
            supportTickets.filter { it.reporterId == userId }
        }
    }


    override fun readAll(): Flow<List<SupportTicket>> {
        return dataFlow.asStateFlow()
    }

    override suspend fun update(supportTicket: SupportTicket) {
        val index = data.indexOfFirst { it.id == supportTicket.id }

        if (index != -1) {
            data[index] = supportTicket
            dataFlow.value = data.toList()
        }
    }

    override suspend fun deleteById(supportTicketId: Int) {
        data.removeIf { it.id == supportTicketId }
        dataFlow.value = data
    }
}

