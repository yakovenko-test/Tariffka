package test.yakovenko.domain.usecase.service

import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.repository.OperatorsRepository
import test.yakovenko.domain.repository.ServicesRepository
import java.util.UUID
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