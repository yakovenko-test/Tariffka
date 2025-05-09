package code.yakovenko.domain.usecase.service

import code.yakovenko.domain.exception.ModelDuplicateException
import code.yakovenko.domain.exception.ModelNotFoundException
import code.yakovenko.domain.model.Service
import code.yakovenko.domain.repository.OperatorsRepository
import code.yakovenko.domain.repository.ServicesRepository
import javax.inject.Inject

class CreateServiceUseCase @Inject constructor(
    private val servicesRepository: ServicesRepository,
    private val operatorsRepository: OperatorsRepository
) {
    operator fun invoke(service: Service): Service {
        if (operatorsRepository.notContainsId(service.operatorId)) {
            throw ModelNotFoundException("Operator", service.operatorId)
        }

        if (servicesRepository.containsId(service.id)) {
            throw ModelDuplicateException("Service", service.id)
        }

        return servicesRepository.create(service)
    }
}