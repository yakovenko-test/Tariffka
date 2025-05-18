package test.yakovenko.domain.usecase.service

import test.yakovenko.domain.exception.ModelDeleteException
import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.repository.ServicesRepository
import java.util.UUID
import javax.inject.Inject

class DeleteServiceUseCase @Inject constructor(
    private val servicesRepository: ServicesRepository,
) {
    suspend operator fun invoke(serviceId: UUID) = runCatching {
        if (!servicesRepository.exists(serviceId)) {
            throw ModelNotFoundException("Service", serviceId)
        }

        if (!servicesRepository.delete(serviceId)) {
            throw ModelDeleteException("Service", serviceId)
        }
    }
}