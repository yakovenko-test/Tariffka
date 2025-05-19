package usecase.feedback

import exception.ModelNotFoundException
import exception.ModelReadException
import repository.FeedbacksRepository
import java.util.*
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