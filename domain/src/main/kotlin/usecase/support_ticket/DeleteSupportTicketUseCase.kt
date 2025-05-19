package usecase.support_ticket

import exception.ModelDeleteException
import exception.ModelNotFoundException
import repository.SupportTicketsRepository
import java.util.*
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