package code.yakovenko.domain.usecase.operator

import code.yakovenko.domain.exception.ModelNotFoundException
import code.yakovenko.domain.model.Operator
import code.yakovenko.domain.repository.OperatorsRepository
import java.util.UUID
import javax.inject.Inject

class ReadOperatorByIdUseCase @Inject constructor(
    private val operatorsRepository: OperatorsRepository
) {
    operator fun invoke(operatorId: UUID): Operator {
        return operatorsRepository.readById(operatorId)
            ?: throw ModelNotFoundException("Operator", operatorId)
    }
}