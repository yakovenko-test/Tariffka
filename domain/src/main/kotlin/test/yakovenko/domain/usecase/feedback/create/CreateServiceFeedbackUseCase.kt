package test.yakovenko.domain.usecase.feedback.create

import test.yakovenko.domain.exception.ModelCreateException
import test.yakovenko.domain.exception.ModelDuplicateException
import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.model.Feedback.ServiceFeedback
import test.yakovenko.domain.repository.FeedbacksRepository
import test.yakovenko.domain.repository.ServicesRepository
import test.yakovenko.domain.repository.UsersRepository
import javax.inject.Inject

class CreateServiceFeedbackUseCase @Inject constructor(
    private val feedbacksRepository: FeedbacksRepository,
    private val usersRepository: UsersRepository,
    private val servicesRepository: ServicesRepository,
) {
    suspend operator fun invoke(feedback: ServiceFeedback) = runCatching {
        if (feedbacksRepository.exists(feedback.id)) {
            throw ModelDuplicateException("Feedback", feedback.id)
        }

        if (!usersRepository.exists(feedback.authorId)) {
            throw ModelNotFoundException("User", feedback.authorId)
        }

        if (!servicesRepository.exists(feedback.serviceId)) {
            throw ModelNotFoundException("Service", feedback.serviceId)
        }

        feedbacksRepository.create(feedback)
            ?: throw ModelCreateException("Feedback", feedback.id)
    }
}