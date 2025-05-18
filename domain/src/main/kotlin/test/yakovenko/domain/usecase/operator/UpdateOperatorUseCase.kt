package test.yakovenko.domain.usecase.operator

import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.exception.ModelUpdateException
import test.yakovenko.domain.model.Operator
import test.yakovenko.domain.repository.OperatorsRepository
import javax.inject.Inject

class UpdateOperatorUseCase @Inject constructor(
    private val operatorsRepository: OperatorsRepository,
) {
    suspend operator fun invoke(operator: Operator) = runCatching {
        if (!operatorsRepository.exists(operator.id)) {
            throw ModelNotFoundException("Operator", operator.id)
        }

        operatorsRepository.update(operator)
            ?: throw ModelUpdateException("Operator", operator.id)
    }
}