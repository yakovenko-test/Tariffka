package test.yakovenko.domain.usecase.support_ticket

import test.yakovenko.domain.exception.ModelDeleteException
import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.repository.SupportTicketsRepository
import java.util.UUID
import javax.inject.Inject

class DeleteSupportTicketUseCase @Inject constructor(
    private val supportTicketsRepository: SupportTicketsRepository,
) {
    suspend operator fun invoke(supportTicketId: UUID) = runCatching {
        if (!supportTicketsRepository.exists(supportTicketId)) {
            throw ModelNotFoundException("SupportTicket", supportTicketId)
        }

        if (!supportTicketsRepository.delete(supportTicketId)) {
            throw ModelDeleteException("SupportTicket", supportTicketId)
        }
    }
}