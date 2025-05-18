package test.yakovenko.domain.usecase.feedback

import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.repository.FeedbacksRepository
import test.yakovenko.domain.repository.ServicesRepository
import java.util.UUID

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