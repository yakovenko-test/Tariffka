package test.yakovenko.domain.usecase.user

import test.yakovenko.domain.exception.ModelDeleteException
import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.repository.UsersRepository
import java.util.UUID
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
) {
    suspend operator fun invoke(userId: UUID) = runCatching {
        if (!usersRepository.exists(userId)) {
            throw ModelNotFoundException("User", userId)
        }

        if (!usersRepository.delete(userId)) {
            throw ModelDeleteException("User", userId)
        }
    }
}