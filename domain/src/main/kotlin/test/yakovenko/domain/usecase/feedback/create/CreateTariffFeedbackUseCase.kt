package test.yakovenko.domain.usecase.feedback.create

import test.yakovenko.domain.exception.ModelCreateException
import test.yakovenko.domain.exception.ModelDuplicateException
import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.model.Feedback.TariffFeedback
import test.yakovenko.domain.repository.FeedbacksRepository
import test.yakovenko.domain.repository.TariffsRepository
import test.yakovenko.domain.repository.UsersRepository
import javax.inject.Inject

class CreateTariffFeedbackUseCase @Inject constructor(
    private val feedbacksRepository: FeedbacksRepository,
    private val usersRepository: UsersRepository,
    private val tariffsRepository: TariffsRepository
) {
    suspend operator fun invoke(feedback: TariffFeedback) = runCatching {
        if (feedbacksRepository.exists(feedback.id)) {
            throw ModelDuplicateException("Feedback", feedback.id)
        }

        if (!usersRepository.exists(feedback.authorId)) {
            throw ModelNotFoundException("User", feedback.authorId)
        }

        if (!tariffsRepository.exists(feedback.tariffId)) {
            throw ModelNotFoundException("Tariff", feedback.tariffId)
        }

        feedbacksRepository.create(feedback)
            ?: throw ModelCreateException("Feedback", feedback.id)
    }
}