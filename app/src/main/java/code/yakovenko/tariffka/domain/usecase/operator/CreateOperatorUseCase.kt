package code.yakovenko.tariffka.domain.usecase.operator

import code.yakovenko.tariffka.domain.exception.ModelDuplicateException
import code.yakovenko.tariffka.domain.model.Operator
import code.yakovenko.tariffka.domain.repository.OperatorRepository
import javax.inject.Inject

class CreateOperatorUseCase @Inject constructor(
    private val operatorRepository: OperatorRepository
) {
    operator fun invoke(operator: Operator): Operator {
        if (operatorRepository.containsId(operator.id)) {
            throw ModelDuplicateException("Operator", operator.id)
        }

        return operatorRepository.create(operator)
    }
}