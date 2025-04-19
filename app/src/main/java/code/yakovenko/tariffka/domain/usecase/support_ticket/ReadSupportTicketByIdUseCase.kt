package code.yakovenko.tariffka.domain.usecase.support_ticket

import code.yakovenko.tariffka.domain.exception.ModelNotFoundException
import code.yakovenko.tariffka.domain.model.SupportTicket
import code.yakovenko.tariffka.domain.repository.SupportTicketRepository
import java.util.UUID
import javax.inject.Inject

class ReadSupportTicketByIdUseCase @Inject constructor(
    private val supportTicketRepository: SupportTicketRepository
) {
    operator fun invoke(supportTicketId: UUID): SupportTicket {
        return supportTicketRepository.readById(supportTicketId)
            ?: throw ModelNotFoundException("SupportTicket", supportTicketId)
    }
}