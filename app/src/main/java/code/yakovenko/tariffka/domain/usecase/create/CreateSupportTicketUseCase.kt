package code.yakovenko.tariffka.domain.usecase.create

import code.yakovenko.tariffka.domain.exception.UserNotFoundException
import code.yakovenko.tariffka.domain.model.SupportTicket
import code.yakovenko.tariffka.domain.repository.SupportTicketRepository
import code.yakovenko.tariffka.domain.repository.UserRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class CreateSupportTicketUseCase @Inject constructor(
    private val supportTicketRepository: SupportTicketRepository,
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(supportTicket: SupportTicket) {
        if (userRepository.readById(supportTicket.reporterId).firstOrNull() == null) {
            throw UserNotFoundException(supportTicket.reporterId)
        }

        supportTicket.assigneeId?.let { assigneeId ->
            if (userRepository.readById(assigneeId).firstOrNull() == null) {
                throw UserNotFoundException(assigneeId)
            }
        }

        supportTicketRepository.create(supportTicket)
    }
}
