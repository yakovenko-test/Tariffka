package code.yakovenko.tariffka.domain.usecase.read

import code.yakovenko.tariffka.domain.model.SupportTicket
import code.yakovenko.tariffka.domain.repository.SupportTicketRepository
import kotlinx.coroutines.flow.Flow

class ReadAllSupportTicketsByUserIdUseCase(
    private val supportTicketRepository: SupportTicketRepository
) {
    operator fun invoke(userId: Int): Flow<List<SupportTicket>> {
        return supportTicketRepository.readByUserId(userId)
    }
}