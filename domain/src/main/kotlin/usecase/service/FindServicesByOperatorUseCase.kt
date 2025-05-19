package usecase.service

import exception.ModelNotFoundException
import repository.OperatorsRepository
import repository.ServicesRepository
import java.util.*
import javax.inject.Inject

class FindServicesByOperatorUseCase @Inject constructor(
    private val servicesRepository: ServicesRepository,
    private val operatorsRepository: OperatorsRepository,
) {
    suspend operator fun invoke(operatorId: UUID) = runCatching {
        if (!operatorsRepository.exists(operatorId)) {
            throw ModelNotFoundException("Operator", operatorId)
        }

        servicesRepository.findByOperator(operatorId)
    }
}