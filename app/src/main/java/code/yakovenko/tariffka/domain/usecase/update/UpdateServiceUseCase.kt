package code.yakovenko.tariffka.domain.usecase.update

import code.yakovenko.tariffka.domain.model.Service
import code.yakovenko.tariffka.domain.repository.ServiceRepository
import javax.inject.Inject

class UpdateServiceUseCase @Inject constructor(
    private val serviceRepository: ServiceRepository
) {
    suspend operator fun invoke(service: Service) {
        serviceRepository.update(service)
    }
}
