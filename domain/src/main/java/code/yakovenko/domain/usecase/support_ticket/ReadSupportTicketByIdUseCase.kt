package code.yakovenko.domain.usecase.support_ticket

import code.yakovenko.domain.exception.ModelNotFoundException
import code.yakovenko.domain.model.SupportTicket
import code.yakovenko.domain.repository.SupportTicketsRepository
import java.util.UUID
import javax.inject.Inject

class ReadSupportTicketByIdUseCase @Inject constructor(
    private val supportTicketsRepository: SupportTicketsRepository
) {
    operator fun invoke(supportTicketId: UUID): SupportTicket {
        return supportTicketsRepository.readById(supportTicketId)
            ?: throw ModelNotFoundException("SupportTicket", supportTicketId)
    }
}