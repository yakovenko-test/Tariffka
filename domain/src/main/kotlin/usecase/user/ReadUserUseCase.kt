package usecase.user

import exception.ModelNotFoundException
import exception.ModelReadException
import repository.UsersRepository
import java.util.*
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