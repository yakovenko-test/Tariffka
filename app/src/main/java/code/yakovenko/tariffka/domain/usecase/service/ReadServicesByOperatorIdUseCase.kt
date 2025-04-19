package code.yakovenko.tariffka.domain.usecase.service

import code.yakovenko.tariffka.domain.exception.ModelNotFoundException
import code.yakovenko.tariffka.domain.model.Service
import code.yakovenko.tariffka.domain.repository.OperatorRepository
import code.yakovenko.tariffka.domain.repository.ServiceRepository
import java.util.UUID
import javax.inject.Inject

class ReadServicesByOperatorIdUseCase @Inject constructor(
    private val serviceRepository: ServiceRepository,
    private val operatorRepository: OperatorRepository
) {
    operator fun invoke(operatorId: UUID): Collection<Service> {
        if (operatorRepository.notContainsId(operatorId)) {
            throw ModelNotFoundException("Operator", operatorId)
        }

        return serviceRepository.readByOperatorId(operatorId)
    }
}