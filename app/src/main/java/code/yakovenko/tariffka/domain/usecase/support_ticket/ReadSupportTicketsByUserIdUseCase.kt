package code.yakovenko.tariffka.domain.usecase.support_ticket

import code.yakovenko.tariffka.domain.exception.ModelNotFoundException
import code.yakovenko.tariffka.domain.model.SupportTicket
import code.yakovenko.tariffka.domain.repository.SupportTicketRepository
import code.yakovenko.tariffka.domain.repository.UserRepository
import java.util.UUID
import javax.inject.Inject

class ReadSupportTicketsByUserIdUseCase @Inject constructor(
    private val supportTicketRepository: SupportTicketRepository,
    private val userRepository: UserRepository
) {
    operator fun invoke(userId: UUID): Collection<SupportTicket> {
        if (userRepository.notContainsId(userId)) {
            throw ModelNotFoundException("User", userId)
        }

        return supportTicketRepository.readByUserId(userId)
    }
}