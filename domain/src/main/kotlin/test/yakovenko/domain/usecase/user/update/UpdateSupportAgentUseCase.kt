package test.yakovenko.domain.usecase.user.update

import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.exception.ModelUpdateException
import test.yakovenko.domain.model.User.SupportAgent
import test.yakovenko.domain.repository.UsersRepository
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