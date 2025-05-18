package test.yakovenko.domain.usecase.user

import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.exception.ModelReadException
import test.yakovenko.domain.repository.UsersRepository
import java.util.UUID
import javax.inject.Inject

class ReadUserUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
) {
    suspend operator fun invoke(userId: UUID) = runCatching {
        if (!usersRepository.exists(userId)) {
            throw ModelNotFoundException("User", userId)
        }

        usersRepository.read(userId)
            ?: throw ModelReadException("User", userId)
    }
}