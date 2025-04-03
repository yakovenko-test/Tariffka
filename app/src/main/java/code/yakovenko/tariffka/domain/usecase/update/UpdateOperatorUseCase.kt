package code.yakovenko.tariffka.domain.usecase.update

import code.yakovenko.tariffka.domain.model.Operator
import code.yakovenko.tariffka.domain.repository.OperatorRepository
import javax.inject.Inject

class UpdateOperatorUseCase @Inject constructor(
    private val operatorRepository: OperatorRepository
) {
    suspend operator fun invoke(operator: Operator) {
        operatorRepository.update(operator)
    }
}
