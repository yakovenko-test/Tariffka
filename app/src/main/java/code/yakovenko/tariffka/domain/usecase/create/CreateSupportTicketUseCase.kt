package code.yakovenko.tariffka.domain.usecase.create

import code.yakovenko.tariffka.domain.model.SupportTicket
import code.yakovenko.tariffka.domain.repository.SupportTicketRepository
import javax.inject.Inject

class CreateSupportTicketUseCase @Inject constructor(
    private val supportTicketRepository: SupportTicketRepository
) {
    suspend operator fun invoke(supportTicket: SupportTicket) {
        supportTicketRepository.create(supportTicket)
    }
}