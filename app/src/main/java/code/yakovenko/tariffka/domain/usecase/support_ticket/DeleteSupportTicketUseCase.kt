package code.yakovenko.tariffka.domain.usecase.support_ticket

import code.yakovenko.tariffka.domain.exception.ModelNotFoundException
import code.yakovenko.tariffka.domain.repository.SupportTicketRepository
import java.util.UUID
import javax.inject.Inject

class DeleteSupportTicketUseCase @Inject constructor(
    private val supportTicketRepository: SupportTicketRepository
) {
    operator fun invoke(supportTicketId: UUID) {
        if (supportTicketRepository.notContainsId(supportTicketId)) {
            throw ModelNotFoundException("SupportTicket", supportTicketId)
        }

        supportTicketRepository.deleteById(supportTicketId)
    }
}