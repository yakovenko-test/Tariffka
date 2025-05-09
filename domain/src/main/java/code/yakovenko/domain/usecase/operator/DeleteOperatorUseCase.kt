package code.yakovenko.domain.usecase.operator

import code.yakovenko.domain.exception.ModelNotFoundException
import code.yakovenko.domain.repository.OperatorsRepository
import java.util.UUID
import javax.inject.Inject

class DeleteOperatorUseCase @Inject constructor(
    private val operatorsRepository: OperatorsRepository
) {
    operator fun invoke(operatorId: UUID) {
        if (operatorsRepository.notContainsId(operatorId)) {
            throw ModelNotFoundException("Operator", operatorId)
        }

        operatorsRepository.deleteById(operatorId)
    }
}