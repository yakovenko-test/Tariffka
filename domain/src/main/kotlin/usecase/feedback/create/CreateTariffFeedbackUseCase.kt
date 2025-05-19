package usecase.feedback.create

import exception.ModelCreateException
import exception.ModelDuplicateException
import exception.ModelNotFoundException
import model.Feedback.TariffFeedback
import repository.FeedbacksRepository
import repository.TariffsRepository
import repository.UsersRepository
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