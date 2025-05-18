package test.yakovenko.domain.usecase.service

import test.yakovenko.domain.exception.ModelCreateException
import test.yakovenko.domain.exception.ModelDuplicateException
import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.model.Service
import test.yakovenko.domain.repository.OperatorsRepository
import test.yakovenko.domain.repository.ServicesRepository
import javax.inject.Inject

class CreateServiceUseCase @Inject constructor(
    private val servicesRepository: ServicesRepository,
    private val operatorsRepository: OperatorsRepository,
) {
    suspend operator fun invoke(service: Service) = runCatching {
        if (servicesRepository.exists(service.id)) {
            throw ModelDuplicateException("Service", service.id)
        }

        if (!operatorsRepository.exists(service.operatorId)) {
            throw ModelNotFoundException("Operator", service.operatorId)
        }

        servicesRepository.create(service)
            ?: throw ModelCreateException("Service", service.id)
    }
}