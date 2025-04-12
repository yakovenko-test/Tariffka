package code.yakovenko.tariffka.domain.usecase.read

import code.yakovenko.tariffka.domain.exception.UserNotFoundException
import code.yakovenko.tariffka.domain.model.SupportTicket
import code.yakovenko.tariffka.domain.repository.SupportTicketRepository
import code.yakovenko.tariffka.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class ReadAllSupportTicketsByUserIdUseCase @Inject constructor(
    private val supportTicketRepository: SupportTicketRepository,
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userId: Uuid): Flow<List<SupportTicket>> {
        if (userRepository.readById(userId).firstOrNull() == null) {
            throw UserNotFoundException(userId)
        }

        return supportTicketRepository.readByUserId(userId)
    }
}
