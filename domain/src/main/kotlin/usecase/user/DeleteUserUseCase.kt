package usecase.user

import exception.ModelDeleteException
import exception.ModelNotFoundException
import repository.UsersRepository
import java.util.*
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