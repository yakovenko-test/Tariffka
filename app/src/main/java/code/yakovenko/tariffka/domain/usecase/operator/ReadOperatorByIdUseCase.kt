package code.yakovenko.tariffka.domain.usecase.operator

import code.yakovenko.tariffka.domain.exception.ModelNotFoundException
import code.yakovenko.tariffka.domain.model.Operator
import code.yakovenko.tariffka.domain.repository.OperatorRepository
import java.util.UUID
import javax.inject.Inject

class ReadOperatorByIdUseCase @Inject constructor(
    private val operatorRepository: OperatorRepository
) {
    operator fun invoke(operatorId: UUID): Operator {
        return operatorRepository.readById(operatorId)
            ?: throw ModelNotFoundException("Operator", operatorId)
    }
}