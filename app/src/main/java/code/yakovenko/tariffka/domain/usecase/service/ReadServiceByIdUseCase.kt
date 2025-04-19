package code.yakovenko.tariffka.domain.usecase.service

import code.yakovenko.tariffka.domain.exception.ModelNotFoundException
import code.yakovenko.tariffka.domain.model.Service
import code.yakovenko.tariffka.domain.repository.ServiceRepository
import java.util.UUID
import javax.inject.Inject

class ReadServiceByIdUseCase @Inject constructor(
    private val serviceRepository: ServiceRepository
) {
    operator fun invoke(serviceId: UUID): Service {
        return serviceRepository.readById(serviceId)
            ?: throw ModelNotFoundException("Service", serviceId)
    }
}