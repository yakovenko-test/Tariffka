package usecase.user.update

import exception.ModelNotFoundException
import exception.ModelUpdateException
import model.User.SupportAgent
import repository.UsersRepository
import javax.inject.Inject

class UpdateSupportAgentUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
) {
    suspend operator fun invoke(user: SupportAgent) = runCatching {
        if (!usersRepository.exists(user.id)) {
            throw ModelNotFoundException("User", user.id)
        }

        usersRepository.update(user) ?: throw ModelUpdateException("User", user.id)
    }
}