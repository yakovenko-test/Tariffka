package code.yakovenko.domain.usecase.support_ticket

import code.yakovenko.domain.exception.ModelNotFoundException
import code.yakovenko.domain.model.SupportTicket
import code.yakovenko.domain.repository.SupportTicketsRepository
import code.yakovenko.domain.repository.UsersRepository
import java.util.UUID
import javax.inject.Inject

class ReadSupportTicketsByUserIdUseCase @Inject constructor(
    private val supportTicketsRepository: SupportTicketsRepository,
    private val usersRepository: UsersRepository
) {
    operator fun invoke(userId: UUID): Collection<SupportTicket> {
        if (usersRepository.notContainsId(userId)) {
            throw ModelNotFoundException("User", userId)
        }

        return supportTicketsRepository.readByUserId(userId)
    }
}