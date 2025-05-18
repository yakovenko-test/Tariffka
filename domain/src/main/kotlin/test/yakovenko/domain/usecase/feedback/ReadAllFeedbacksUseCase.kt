package test.yakovenko.domain.usecase.feedback

import test.yakovenko.domain.repository.FeedbacksRepository

class ReadAllFeedbacksUseCase(
    private val feedbacksRepository: FeedbacksRepository
) {
    suspend operator fun invoke() = runCatching { feedbacksRepository.readAll() }
}