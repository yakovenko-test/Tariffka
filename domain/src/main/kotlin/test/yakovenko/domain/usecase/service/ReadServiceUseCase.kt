package test.yakovenko.domain.usecase.service

import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.exception.ModelReadException
import test.yakovenko.domain.repository.ServicesRepository
import java.util.UUID
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