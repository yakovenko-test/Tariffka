package code.yakovenko.tariffka.domain.usecase.read

import code.yakovenko.tariffka.domain.exception.SupportTicketNotFoundException
import code.yakovenko.tariffka.domain.model.SupportTicket
import code.yakovenko.tariffka.domain.repository.SupportTicketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class ReadSupportTicketByIdUseCase @Inject constructor(
    private val supportTicketRepository: SupportTicketRepository
) {
    suspend operator fun invoke(supportTicketId: Uuid): Flow<SupportTicket?> {
        if (supportTicketRepository.readById(supportTicketId).firstOrNull() == null) {
            throw SupportTicketNotFoundException(supportTicketId)
        }

        return supportTicketRepository.readById(supportTicketId)
    }
}
