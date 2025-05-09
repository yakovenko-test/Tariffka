package code.yakovenko.domain.usecase.support_ticket

import code.yakovenko.domain.exception.ModelNotFoundException
import code.yakovenko.domain.repository.SupportTicketsRepository
import java.util.UUID
import javax.inject.Inject

class DeleteSupportTicketUseCase @Inject constructor(
    private val supportTicketsRepository: SupportTicketsRepository
) {
    operator fun invoke(supportTicketId: UUID) {
        if (supportTicketsRepository.notContainsId(supportTicketId)) {
            throw ModelNotFoundException("SupportTicket", supportTicketId)
        }

        supportTicketsRepository.deleteById(supportTicketId)
    }
}