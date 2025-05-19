package usecase.feedback

import exception.ModelNotFoundException
import repository.FeedbacksRepository
import repository.UsersRepository
import java.util.*
import javax.inject.Inject

class FindFeedbacksByAuthorUseCase @Inject constructor(
    private val feedbacksRepository: FeedbacksRepository,
    private val usersRepository: UsersRepository,
) {
    suspend operator fun invoke(userId: UUID) = runCatching {
        if (!usersRepository.exists(userId)) {
            throw ModelNotFoundException("User", userId)
        }

        feedbacksRepository.findByAuthor(userId)
    }
}