package code.yakovenko.tariffka.domain.usecase.support_ticket

import code.yakovenko.tariffka.domain.exception.ModelNotFoundException
import code.yakovenko.tariffka.domain.model.SupportTicket
import code.yakovenko.tariffka.domain.repository.SupportTicketRepository
import code.yakovenko.tariffka.domain.repository.UserRepository
import javax.inject.Inject

class UpdateSupportTicketUseCase @Inject constructor(
    private val supportTicketRepository: SupportTicketRepository,
    private val userRepository: UserRepository
) {
    operator fun invoke(supportTicket: SupportTicket): SupportTicket? {
        if (userRepository.notContainsId(supportTicket.reporterId)) {
            throw ModelNotFoundException("User", supportTicket.reporterId)
        }

        supportTicket.assigneeId?.let { assigneeId ->
            if (userRepository.notContainsId(assigneeId)) {
                throw ModelNotFoundException("User", assigneeId)
            }
        }

        if (supportTicketRepository.notContainsId(supportTicket.id)) {
            throw ModelNotFoundException("SupportTicket", supportTicket.id)
        }

        return supportTicketRepository.update(supportTicket)
    }
}