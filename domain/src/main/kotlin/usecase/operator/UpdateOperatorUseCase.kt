package usecase.operator

import exception.ModelNotFoundException
import exception.ModelUpdateException
import model.Operator
import repository.OperatorsRepository
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