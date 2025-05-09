package code.yakovenko.domain.usecase.operator

import code.yakovenko.domain.exception.ModelNotFoundException
import code.yakovenko.domain.model.Operator
import code.yakovenko.domain.repository.OperatorsRepository
import javax.inject.Inject

class UpdateOperatorUseCase @Inject constructor(
    private val operatorsRepository: OperatorsRepository
) {
    operator fun invoke(operator: Operator): Operator? {
        if (operatorsRepository.notContainsId(operator.id)) {
            throw ModelNotFoundException("Operator", operator.id)
        }

        return operatorsRepository.update(operator)
    }
}