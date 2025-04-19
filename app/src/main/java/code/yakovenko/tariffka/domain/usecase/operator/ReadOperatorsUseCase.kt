package code.yakovenko.tariffka.domain.usecase.operator

import code.yakovenko.tariffka.domain.model.Operator
import code.yakovenko.tariffka.domain.repository.OperatorRepository
import javax.inject.Inject

class ReadOperatorsUseCase @Inject constructor(
    private val operatorRepository: OperatorRepository
) {
    operator fun invoke(): Collection<Operator> {
        return operatorRepository.readAll()
    }
}