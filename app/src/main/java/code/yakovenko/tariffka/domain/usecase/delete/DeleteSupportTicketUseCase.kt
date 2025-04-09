package code.yakovenko.tariffka.domain.usecase.delete

import code.yakovenko.tariffka.domain.repository.SupportTicketRepository

class DeleteSupportTicketUseCase(
    private val supportTicketRepository: SupportTicketRepository
) {
    suspend operator fun invoke(supportTicketId: Int) {
        supportTicketRepository.deleteById(supportTicketId)
    }
}