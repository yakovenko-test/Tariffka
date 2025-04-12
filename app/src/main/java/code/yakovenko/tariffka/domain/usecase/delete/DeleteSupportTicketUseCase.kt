package code.yakovenko.tariffka.domain.usecase.delete

import code.yakovenko.tariffka.domain.exception.SupportTicketNotFoundException
import code.yakovenko.tariffka.domain.repository.SupportTicketRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class DeleteSupportTicketUseCase @Inject constructor(
    private val supportTicketRepository: SupportTicketRepository
) {
    suspend operator fun invoke(supportTicketId: Uuid) {
        if (supportTicketRepository.readById(supportTicketId).firstOrNull() == null) {
            throw SupportTicketNotFoundException(supportTicketId)
        }

        supportTicketRepository.deleteById(supportTicketId)
    }
}