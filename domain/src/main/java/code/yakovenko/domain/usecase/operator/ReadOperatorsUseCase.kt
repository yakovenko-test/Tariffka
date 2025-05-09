package code.yakovenko.domain.usecase.operator

import code.yakovenko.domain.model.Operator
import code.yakovenko.domain.repository.OperatorsRepository
import javax.inject.Inject

class ReadOperatorsUseCase @Inject constructor(
    private val operatorsRepository: OperatorsRepository
) {
    operator fun invoke(): Collection<Operator> {
        return operatorsRepository.readAll()
    }
}