package usecase.operator

import exception.ModelCreateException
import exception.ModelDuplicateException
import model.Operator
import repository.OperatorsRepository
import javax.inject.Inject

class CreateOperatorUseCase @Inject constructor(
    private val operatorsRepository: OperatorsRepository,
) {
    suspend operator fun invoke(operator: Operator) = runCatching {
        if (operatorsRepository.exists(operator.id)) {
            throw ModelDuplicateException("Operator", operator.id)
        }

        operatorsRepository.create(operator)
            ?: throw ModelCreateException("Operator", operator.id)
    }
}