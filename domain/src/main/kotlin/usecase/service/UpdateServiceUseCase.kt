package usecase.service

import exception.ModelNotFoundException
import exception.ModelUpdateException
import model.Service
import repository.OperatorsRepository
import repository.ServicesRepository
import javax.inject.Inject

class UpdateServiceUseCase @Inject constructor(
    private val servicesRepository: ServicesRepository,
    private val operatorsRepository: OperatorsRepository,
) {
    suspend operator fun invoke(service: Service) = runCatching {
        if (!servicesRepository.exists(service.id)) {
            throw ModelNotFoundException("Service", service.id)
        }

        if (!operatorsRepository.exists(service.operatorId)) {
            throw ModelNotFoundException("Operator", service.operatorId)
        }

        servicesRepository.update(service)
            ?: throw ModelUpdateException("Service", service.id)
    }
}