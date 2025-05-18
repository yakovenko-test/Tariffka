package test.yakovenko.domain.usecase.feedback

import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.exception.ModelReadException
import test.yakovenko.domain.repository.FeedbacksRepository
import java.util.UUID
import javax.inject.Inject

class ReadFeedbackUseCase @Inject constructor(
    private val feedbacksRepository: FeedbacksRepository,
) {
    suspend operator fun invoke(feedbackId: UUID) = runCatching {
        if (!feedbacksRepository.exists(feedbackId)) {
            throw ModelNotFoundException("Feedback", feedbackId)
        }

        feedbacksRepository.read(feedbackId)
            ?: throw ModelReadException("Feedback", feedbackId)
    }
}