package code.yakovenko.tariffka.domain.usecase.service

import code.yakovenko.tariffka.domain.exception.ModelNotFoundException
import code.yakovenko.tariffka.domain.model.Service
import code.yakovenko.tariffka.domain.repository.OperatorRepository
import code.yakovenko.tariffka.domain.repository.ServiceRepository
import javax.inject.Inject

class UpdateServiceUseCase @Inject constructor(
    private val serviceRepository: ServiceRepository,
    private val operatorRepository: OperatorRepository
) {
    operator fun invoke(service: Service): Service? {
        if (operatorRepository.notContainsId(service.operatorId)) {
            throw ModelNotFoundException("Operator", service.operatorId)
        }

        if (serviceRepository.notContainsId(service.id)) {
            throw ModelNotFoundException("Service", service.id)
        }

        return serviceRepository.update(service)
    }
}