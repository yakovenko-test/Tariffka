package code.yakovenko.tariffka.domain.usecase.create

import code.yakovenko.tariffka.domain.model.User
import code.yakovenko.tariffka.domain.repository.UserRepository

class CreateUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User) {
        userRepository.create(user)
    }
}
