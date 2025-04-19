package code.yakovenko.tariffka.domain.usecase.service

import code.yakovenko.tariffka.domain.exception.ModelNotFoundException
import code.yakovenko.tariffka.domain.repository.ServiceRepository
import java.util.UUID
import javax.inject.Inject

class DeleteServiceUseCase @Inject constructor(
    private val serviceRepository: ServiceRepository
) {
    operator fun invoke(serviceId: UUID) {
        if (serviceRepository.notContainsId(serviceId)) {
            throw ModelNotFoundException("Service", serviceId)
        }

        serviceRepository.deleteById(serviceId)
    }
}