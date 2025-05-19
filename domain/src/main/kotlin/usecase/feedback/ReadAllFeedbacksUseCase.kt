package usecase.feedback

import repository.FeedbacksRepository

class ReadAllFeedbacksUseCase(
    private val feedbacksRepository: FeedbacksRepository
) {
    suspend operator fun invoke() = runCatching { feedbacksRepository.readAll() }
}