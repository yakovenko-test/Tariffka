package code.yakovenko.domain.usecase.service

import code.yakovenko.domain.exception.ModelNotFoundException
import code.yakovenko.domain.repository.ServicesRepository
import java.util.UUID
import javax.inject.Inject

class DeleteServiceUseCase @Inject constructor(
    private val servicesRepository: ServicesRepository
) {
    operator fun invoke(serviceId: UUID) {
        if (servicesRepository.notContainsId(serviceId)) {
            throw ModelNotFoundException("Service", serviceId)
        }

        servicesRepository.deleteById(serviceId)
    }
}