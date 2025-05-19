package usecase.support_ticket

import exception.ModelCreateException
import exception.ModelDuplicateException
import exception.ModelNotFoundException
import model.SupportTicket
import repository.SupportTicketsRepository
import repository.UsersRepository
import javax.inject.Inject

class CreateSupportTicketUseCase @Inject constructor(
    private val supportTicketsRepository: SupportTicketsRepository,
    private val usersRepository: UsersRepository,
) {
    suspend operator fun invoke(supportTicket: SupportTicket) = runCatching {
        if (supportTicketsRepository.exists(supportTicket.id)) {
            throw ModelDuplicateException("SupportTicket", supportTicket.id)
        }

        if (!usersRepository.exists(supportTicket.reporterId)) {
            throw ModelNotFoundException("User", supportTicket.reporterId)
        }

        if (supportTicket.assigneeId != null && !usersRepository.exists(supportTicket.assigneeId)) {
            throw ModelNotFoundException("User", supportTicket.assigneeId)
        }

        supportTicketsRepository.create(supportTicket)
            ?: throw ModelCreateException("SupportTicket", supportTicket.id)
    }
}