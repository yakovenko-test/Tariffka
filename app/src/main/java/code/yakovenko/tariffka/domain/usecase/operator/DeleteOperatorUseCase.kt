package code.yakovenko.tariffka.domain.usecase.operator

import code.yakovenko.tariffka.domain.exception.ModelNotFoundException
import code.yakovenko.tariffka.domain.repository.OperatorRepository
import java.util.UUID
import javax.inject.Inject

class DeleteOperatorUseCase @Inject constructor(
    private val operatorRepository: OperatorRepository
) {
    operator fun invoke(operatorId: UUID) {
        if (operatorRepository.notContainsId(operatorId)) {
            throw ModelNotFoundException("Operator", operatorId)
        }

        operatorRepository.deleteById(operatorId)
    }
}