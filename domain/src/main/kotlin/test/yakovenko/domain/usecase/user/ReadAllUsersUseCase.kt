package test.yakovenko.domain.usecase.user

import test.yakovenko.domain.repository.UsersRepository
import javax.inject.Inject

class ReadAllUsersUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
) {
    suspend operator fun invoke() = runCatching { usersRepository.readAll() }
}