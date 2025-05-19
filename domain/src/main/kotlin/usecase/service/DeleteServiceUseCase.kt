package usecase.service

import exception.ModelDeleteException
import exception.ModelNotFoundException
import repository.ServicesRepository
import java.util.*
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