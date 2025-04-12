package code.yakovenko.tariffka.domain.usecase.delete

import code.yakovenko.tariffka.domain.exception.UserNotFoundException
import code.yakovenko.tariffka.domain.repository.UserRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class DeleteUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userId: Uuid) {
        if (userRepository.readById(userId).firstOrNull() == null) {
            throw UserNotFoundException(userId)
        }

        userRepository.deleteById(userId)
    }
}
