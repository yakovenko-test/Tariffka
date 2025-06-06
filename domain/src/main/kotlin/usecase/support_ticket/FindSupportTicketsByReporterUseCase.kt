package usecase.support_ticket

import exception.ModelNotFoundException
import repository.SupportTicketsRepository
import repository.UsersRepository
import java.util.*
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