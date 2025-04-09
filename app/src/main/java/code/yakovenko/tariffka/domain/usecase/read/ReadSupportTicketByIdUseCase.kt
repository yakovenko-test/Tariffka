package code.yakovenko.tariffka.domain.usecase.read

import code.yakovenko.tariffka.domain.model.SupportTicket
import code.yakovenko.tariffka.domain.repository.SupportTicketRepository
import kotlinx.coroutines.flow.Flow

class ReadSupportTicketByIdUseCase(
    private val supportTicketRepository: SupportTicketRepository
) {
    operator fun invoke(supportTicketId: Int): Flow<SupportTicket?> {
        return supportTicketRepository.readById(supportTicketId)
    }
}
