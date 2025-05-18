package test.yakovenko.domain.usecase.support_ticket

import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.exception.ModelReadException
import test.yakovenko.domain.repository.SupportTicketsRepository
import java.util.UUID
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