package code.yakovenko.tariffka.domain.usecase.read

import code.yakovenko.tariffka.domain.model.Service
import code.yakovenko.tariffka.domain.repository.ServiceRepository
import kotlinx.coroutines.flow.Flow

class ReadServiceByIdUseCase(
    private val serviceRepository: ServiceRepository
) {
    operator fun invoke(serviceId: Int): Flow<Service?> {
        return serviceRepository.readById(serviceId)
    }
}