package code.yakovenko.domain.usecase.service

import code.yakovenko.domain.exception.ModelNotFoundException
import code.yakovenko.domain.model.Service
import code.yakovenko.domain.repository.OperatorsRepository
import code.yakovenko.domain.repository.ServicesRepository
import java.util.UUID
import javax.inject.Inject

class ReadServicesByOperatorIdUseCase @Inject constructor(
    private val servicesRepository: ServicesRepository,
    private val operatorsRepository: OperatorsRepository
) {
    operator fun invoke(operatorId: UUID): Collection<Service> {
        if (operatorsRepository.notContainsId(operatorId)) {
            throw ModelNotFoundException("Operator", operatorId)
        }

        return servicesRepository.readByOperatorId(operatorId)
    }
}