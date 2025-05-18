package test.yakovenko.domain.usecase.user.create

import test.yakovenko.domain.exception.ModelCreateException
import test.yakovenko.domain.exception.ModelDuplicateException
import test.yakovenko.domain.model.User.SupportAgent
import test.yakovenko.domain.repository.UsersRepository
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