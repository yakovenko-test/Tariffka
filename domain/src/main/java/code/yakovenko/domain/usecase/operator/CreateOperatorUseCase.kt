package code.yakovenko.domain.usecase.operator

import code.yakovenko.domain.exception.ModelDuplicateException
import code.yakovenko.domain.model.Operator
import code.yakovenko.domain.repository.OperatorsRepository
import javax.inject.Inject

class CreateOperatorUseCase @Inject constructor(
    private val operatorsRepository: OperatorsRepository
) {
    operator fun invoke(operator: Operator): Operator {
        if (operatorsRepository.containsId(operator.id)) {
            throw ModelDuplicateException("Operator", operator.id)
        }

        return operatorsRepository.create(operator)
    }
}