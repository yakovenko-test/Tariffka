package code.yakovenko.domain.usecase.user

import code.yakovenko.domain.exception.ModelNotFoundException
import code.yakovenko.domain.repository.UsersRepository
import java.util.UUID
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    operator fun invoke(userId: UUID) {
        if (usersRepository.notContainsId(userId)) {
            throw ModelNotFoundException("User", userId)
        }

        usersRepository.deleteById(userId)
    }
}