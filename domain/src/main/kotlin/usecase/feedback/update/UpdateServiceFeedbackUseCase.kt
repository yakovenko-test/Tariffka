package usecase.feedback.update

import exception.ModelNotFoundException
import exception.ModelUpdateException
import model.Feedback.ServiceFeedback
import repository.FeedbacksRepository
import repository.ServicesRepository
import repository.UsersRepository
import javax.inject.Inject

class UpdateServiceFeedbackUseCase @Inject constructor(
    private val feedbacksRepository: FeedbacksRepository,
    private val usersRepository: UsersRepository,
    private val servicesRepository: ServicesRepository,
) {
    suspend operator fun invoke(feedback: ServiceFeedback) = runCatching {
        if (!feedbacksRepository.exists(feedback.id)) {
            throw ModelNotFoundException("Feedback", feedback.id)
        }

        if (!usersRepository.exists(feedback.authorId)) {
            throw ModelNotFoundException("User", feedback.authorId)
        }

        if (!servicesRepository.exists(feedback.serviceId)) {
            throw ModelNotFoundException("Service", feedback.serviceId)
        }

        feedbacksRepository.update(feedback)
            ?: throw ModelUpdateException("Feedback", feedback.id)
    }
}