package code.yakovenko.tariffka.domain.usecase.create

import code.yakovenko.tariffka.domain.model.Service
import code.yakovenko.tariffka.domain.repository.ServiceRepository

class CreateServiceUseCase(
    private val serviceRepository: ServiceRepository
) {
    suspend operator fun invoke(service: Service) {
        serviceRepository.create(service)
    }
}
