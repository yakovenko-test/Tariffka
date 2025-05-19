package usecase.feedback

import exception.ModelNotFoundException
import repository.FeedbacksRepository
import repository.ServicesRepository
import java.util.*

class FindServiceFeedbacksUseCase(
    private val feedbacksRepository: FeedbacksRepository,
    private val servicesRepository: ServicesRepository,
) {
    suspend operator fun invoke(serviceId: UUID) = runCatching {
        if (!servicesRepository.exists(serviceId)) {
            throw ModelNotFoundException("Service", serviceId)
        }

        feedbacksRepository.findForService(serviceId)
    }
}