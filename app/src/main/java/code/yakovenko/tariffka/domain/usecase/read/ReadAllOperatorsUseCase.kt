package code.yakovenko.tariffka.domain.usecase.read

import code.yakovenko.tariffka.domain.model.Operator
import code.yakovenko.tariffka.domain.repository.OperatorRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadAllOperatorsUseCase @Inject constructor(
    private val operatorRepository: OperatorRepository
) {
    operator fun invoke(): Flow<List<Operator>> {
        return operatorRepository.readAll()
    }
}
