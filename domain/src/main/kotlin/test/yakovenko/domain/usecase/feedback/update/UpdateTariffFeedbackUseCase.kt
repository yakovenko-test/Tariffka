package test.yakovenko.domain.usecase.feedback.update

import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.exception.ModelUpdateException
import test.yakovenko.domain.model.Feedback.TariffFeedback
import test.yakovenko.domain.repository.FeedbacksRepository
import test.yakovenko.domain.repository.TariffsRepository
import test.yakovenko.domain.repository.UsersRepository
import javax.inject.Inject

class UpdateTariffFeedbackUseCase @Inject constructor(
    private val feedbacksRepository: FeedbacksRepository,
    private val usersRepository: UsersRepository,
    private val tariffsRepository: TariffsRepository,
) {
    suspend operator fun invoke(feedback: TariffFeedback) = runCatching {
        if (!feedbacksRepository.exists(feedback.id)) {
            throw ModelNotFoundException("Feedback", feedback.id)
        }

        if (!usersRepository.exists(feedback.authorId)) {
            throw ModelNotFoundException("User", feedback.authorId)
        }

        if (!tariffsRepository.exists(feedback.tariffId)) {
            throw ModelNotFoundException("Tariff", feedback.tariffId)
        }

        feedbacksRepository.update(feedback)
            ?: throw ModelUpdateException("Feedback", feedback.id)
    }
}