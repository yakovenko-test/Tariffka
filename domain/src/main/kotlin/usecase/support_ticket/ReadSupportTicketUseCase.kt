package usecase.support_ticket

import exception.ModelNotFoundException
import exception.ModelReadException
import repository.SupportTicketsRepository
import java.util.*
import javax.inject.Inject

class ReadSupportTicketUseCase @Inject constructor(
    private val supportTicketsRepository: SupportTicketsRepository,
) {
    suspend operator fun invoke(supportTicketId: UUID) = runCatching {
        if (!supportTicketsRepository.exists(supportTicketId)) {
            throw ModelNotFoundException("SupportTicket", supportTicketId)
        }

        supportTicketsRepository.read(supportTicketId)
            ?: throw ModelReadException("SupportTicket", supportTicketId)
    }
}