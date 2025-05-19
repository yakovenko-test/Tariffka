package usecase.support_ticket

import exception.ModelNotFoundException
import exception.ModelUpdateException
import model.SupportTicket
import repository.SupportTicketsRepository
import repository.UsersRepository
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