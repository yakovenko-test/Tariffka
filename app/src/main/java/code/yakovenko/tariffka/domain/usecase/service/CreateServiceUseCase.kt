package code.yakovenko.tariffka.domain.usecase.service

import code.yakovenko.tariffka.domain.exception.ModelDuplicateException
import code.yakovenko.tariffka.domain.exception.ModelNotFoundException
import code.yakovenko.tariffka.domain.model.Service
import code.yakovenko.tariffka.domain.repository.OperatorRepository
import code.yakovenko.tariffka.domain.repository.ServiceRepository
import javax.inject.Inject

class CreateServiceUseCase @Inject constructor(
    private val serviceRepository: ServiceRepository,
    private val operatorRepository: OperatorRepository
) {
    operator fun invoke(service: Service): Service {
        if (operatorRepository.notContainsId(service.operatorId)) {
            throw ModelNotFoundException("Operator", service.operatorId)
        }

        if (serviceRepository.containsId(service.id)) {
            throw ModelDuplicateException("Service", service.id)
        }

        return serviceRepository.create(service)
    }
}