package code.yakovenko.tariffka.domain.usecase.read

import code.yakovenko.tariffka.domain.model.Service
import code.yakovenko.tariffka.domain.repository.ServiceRepository
import kotlinx.coroutines.flow.Flow

class ReadAllServicesByOperatorIdUseCase(
    private val serviceRepository: ServiceRepository
) {
    operator fun invoke(operatorId: Int): Flow<List<Service>> {
        return serviceRepository.readByOperatorId(operatorId)
    }
}
