package test.yakovenko.domain.usecase.feedback

import test.yakovenko.domain.exception.ModelDeleteException
import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.repository.FeedbacksRepository
import java.util.UUID
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