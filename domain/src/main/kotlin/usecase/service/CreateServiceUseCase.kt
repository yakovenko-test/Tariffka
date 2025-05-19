package usecase.service

import exception.ModelCreateException
import exception.ModelDuplicateException
import exception.ModelNotFoundException
import model.Service
import repository.OperatorsRepository
import repository.ServicesRepository
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