package test.yakovenko.domain.usecase.operator

import test.yakovenko.domain.exception.ModelCreateException
import test.yakovenko.domain.exception.ModelDuplicateException
import test.yakovenko.domain.model.Operator
import test.yakovenko.domain.repository.OperatorsRepository
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