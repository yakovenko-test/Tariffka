package code.yakovenko.tariffka.domain.usecase.user

import code.yakovenko.tariffka.domain.exception.ModelNotFoundException
import code.yakovenko.tariffka.domain.repository.UserRepository
import java.util.UUID
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(userId: UUID) {
        if (userRepository.notContainsId(userId)) {
            throw ModelNotFoundException("User", userId)
        }

        userRepository.deleteById(userId)
    }
}