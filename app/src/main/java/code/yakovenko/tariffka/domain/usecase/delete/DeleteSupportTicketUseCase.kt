package code.yakovenko.tariffka.domain.usecase.delete

import code.yakovenko.tariffka.domain.repository.SupportTicketRepository
import javax.inject.Inject

class DeleteSupportTicketUseCase @Inject constructor(
    private val supportTicketRepository: SupportTicketRepository
) {
    suspend operator fun invoke(supportTicketId: IdType) {
        supportTicketRepository.deleteById(supportTicketId)
    }
}