package code.yakovenko.tariffka.domain.usecase.read

import code.yakovenko.tariffka.domain.model.Operator
import code.yakovenko.tariffka.domain.repository.OperatorRepository
import kotlinx.coroutines.flow.Flow

class ReadOperatorByIdUseCase(
    private val operatorRepository: OperatorRepository
) {
    operator fun invoke(operatorId: Int): Flow<Operator?> {
        return operatorRepository.readById(operatorId)
    }
}