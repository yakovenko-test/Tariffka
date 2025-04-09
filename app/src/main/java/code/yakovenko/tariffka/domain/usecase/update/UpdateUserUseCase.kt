package code.yakovenko.tariffka.domain.usecase.update

import code.yakovenko.tariffka.domain.model.User
import code.yakovenko.tariffka.domain.repository.UserRepository

class UpdateUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User) {
        userRepository.update(user)
    }
}
