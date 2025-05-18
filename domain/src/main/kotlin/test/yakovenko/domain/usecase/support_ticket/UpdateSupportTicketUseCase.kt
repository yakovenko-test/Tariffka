package test.yakovenko.domain.usecase.support_ticket

import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.exception.ModelUpdateException
import test.yakovenko.domain.model.SupportTicket
import test.yakovenko.domain.repository.SupportTicketsRepository
import test.yakovenko.domain.repository.UsersRepository
import javax.inject.Inject

class UpdateSupportTicketUseCase @Inject constructor(
    private val supportTicketsRepository: SupportTicketsRepository,
    private val usersRepository: UsersRepository,
) {
    suspend operator fun invoke(supportTicket: SupportTicket) = runCatching {
        if (!supportTicketsRepository.exists(supportTicket.id)) {
            throw ModelNotFoundException("SupportTicket", supportTicket.id)
        }

        if (!usersRepository.exists(supportTicket.reporterId)) {
            throw ModelNotFoundException("User", supportTicket.reporterId)
        }

        if (supportTicket.assigneeId != null && !usersRepository.exists(supportTicket.assigneeId)) {
            throw ModelNotFoundException("User", supportTicket.assigneeId)
        }

        supportTicketsRepository.update(supportTicket)
            ?: throw ModelUpdateException("SupportTicket", supportTicket.id)
    }
}