package code.yakovenko.domain.usecase.support_ticket

import code.yakovenko.domain.exception.ModelDuplicateException
import code.yakovenko.domain.exception.ModelNotFoundException
import code.yakovenko.domain.model.SupportTicket
import code.yakovenko.domain.repository.SupportTicketsRepository
import code.yakovenko.domain.repository.UsersRepository
import javax.inject.Inject

class CreateSupportTicketUseCase @Inject constructor(
    private val supportTicketsRepository: SupportTicketsRepository,
    private val usersRepository: UsersRepository
) {
    operator fun invoke(supportTicket: SupportTicket): SupportTicket {
        if (usersRepository.notContainsId(supportTicket.reporterId)) {
            throw ModelNotFoundException("User", supportTicket.reporterId)
        }

        supportTicket.assigneeId?.let { assigneeId ->
            if (usersRepository.notContainsId(assigneeId)) {
                throw ModelNotFoundException("User", assigneeId)
            }
        }

        if (supportTicketsRepository.containsId(supportTicket.id)) {
            throw ModelDuplicateException("SupportTicket", supportTicket.id)
        }

        return supportTicketsRepository.create(supportTicket)
    }
}