package test.yakovenko.domain.usecase.support_ticket

import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.repository.SupportTicketsRepository
import test.yakovenko.domain.repository.UsersRepository
import java.util.UUID
import javax.inject.Inject

class FindSupportTicketsByReporterUseCase @Inject constructor(
    private val supportTicketsRepository: SupportTicketsRepository,
    private val usersRepository: UsersRepository,
) {
    suspend operator fun invoke(userId: UUID) = runCatching {
        if (!usersRepository.exists(userId)) {
            throw ModelNotFoundException("User", userId)
        }

        supportTicketsRepository.findByReporter(userId)
    }
}