package code.yakovenko.tariffka.domain.usecase.update

import code.yakovenko.tariffka.domain.model.SupportTicket
import code.yakovenko.tariffka.domain.repository.SupportTicketRepository
import javax.inject.Inject

class UpdateSupportTicketUseCase @Inject constructor(
    private val supportTicketRepository: SupportTicketRepository
) {
    suspend operator fun invoke(supportTicket: SupportTicket) {
        supportTicketRepository.update(supportTicket)
    }
}