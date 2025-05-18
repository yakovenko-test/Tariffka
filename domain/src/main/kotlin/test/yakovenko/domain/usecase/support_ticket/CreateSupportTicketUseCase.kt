package test.yakovenko.domain.usecase.support_ticket

import test.yakovenko.domain.exception.ModelCreateException
import test.yakovenko.domain.exception.ModelDuplicateException
import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.model.SupportTicket
import test.yakovenko.domain.repository.SupportTicketsRepository
import test.yakovenko.domain.repository.UsersRepository
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