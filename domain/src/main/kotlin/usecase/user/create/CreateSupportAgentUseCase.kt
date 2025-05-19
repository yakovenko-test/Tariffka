package usecase.user.create

import exception.ModelCreateException
import exception.ModelDuplicateException
import model.User.SupportAgent
import repository.UsersRepository
import javax.inject.Inject

class CreateSupportAgentUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
) {
    suspend operator fun invoke(user: SupportAgent) = runCatching {
        if (usersRepository.exists(user.id)) {
            throw ModelDuplicateException("User", user.id)
        }

        usersRepository.create(user) ?: throw ModelCreateException("User", user.id)
    }
}