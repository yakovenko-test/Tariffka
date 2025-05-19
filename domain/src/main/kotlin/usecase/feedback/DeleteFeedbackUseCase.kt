package usecase.feedback

import exception.ModelDeleteException
import exception.ModelNotFoundException
import repository.FeedbacksRepository
import java.util.*
import javax.inject.Inject

class DeleteFeedbackUseCase @Inject constructor(
    private val feedbacksRepository: FeedbacksRepository,
) {
    suspend operator fun invoke(feedbackId: UUID) = runCatching {
        if (!feedbacksRepository.exists(feedbackId)) {
            throw ModelNotFoundException("Feedback", feedbackId)
        }

        if (!feedbacksRepository.delete(feedbackId)) {
            throw ModelDeleteException("Feedback", feedbackId)
        }
    }
}