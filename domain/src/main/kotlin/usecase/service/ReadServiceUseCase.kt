package usecase.service

import exception.ModelNotFoundException
import exception.ModelReadException
import repository.ServicesRepository
import java.util.*
import javax.inject.Inject

class ReadServiceUseCase @Inject constructor(
    private val servicesRepository: ServicesRepository,
) {
    suspend operator fun invoke(serviceId: UUID) = runCatching {
        if (!servicesRepository.exists(serviceId)) {
            throw ModelNotFoundException("Service", serviceId)
        }

        servicesRepository.read(serviceId)
            ?: throw ModelReadException("Service", serviceId)
    }
}