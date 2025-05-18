package test.yakovenko.domain.usecase.feedback

import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.repository.FeedbacksRepository
import test.yakovenko.domain.repository.UsersRepository
import java.util.UUID
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