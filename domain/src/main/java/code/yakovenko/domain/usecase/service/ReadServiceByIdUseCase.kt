package code.yakovenko.domain.usecase.service

import code.yakovenko.domain.exception.ModelNotFoundException
import code.yakovenko.domain.model.Service
import code.yakovenko.domain.repository.ServicesRepository
import java.util.UUID
import javax.inject.Inject

class ReadServiceByIdUseCase @Inject constructor(
    private val servicesRepository: ServicesRepository
) {
    operator fun invoke(serviceId: UUID): Service {
        return servicesRepository.readById(serviceId)
            ?: throw ModelNotFoundException("Service", serviceId)
    }
}