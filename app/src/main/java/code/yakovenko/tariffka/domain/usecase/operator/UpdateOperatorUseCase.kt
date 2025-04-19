package code.yakovenko.tariffka.domain.usecase.operator

import code.yakovenko.tariffka.domain.exception.ModelNotFoundException
import code.yakovenko.tariffka.domain.model.Operator
import code.yakovenko.tariffka.domain.repository.OperatorRepository
import javax.inject.Inject

class UpdateOperatorUseCase @Inject constructor(
    private val operatorRepository: OperatorRepository
) {
    operator fun invoke(operator: Operator): Operator? {
        if (operatorRepository.notContainsId(operator.id)) {
            throw ModelNotFoundException("Operator", operator.id)
        }

        return operatorRepository.update(operator)
    }
}