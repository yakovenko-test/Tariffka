package code.yakovenko.tariffka.domain.usecase.delete

import code.yakovenko.tariffka.domain.repository.UserRepository

class DeleteUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userId: Int) {
        userRepository.deleteById(userId)
    }
}
