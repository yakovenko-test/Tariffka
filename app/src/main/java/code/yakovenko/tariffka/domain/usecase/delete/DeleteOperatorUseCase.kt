package code.yakovenko.tariffka.domain.usecase.delete

import code.yakovenko.tariffka.domain.repository.OperatorRepository

class DeleteOperatorUseCase(
    private val operatorRepository: OperatorRepository
) {
    suspend operator fun invoke(operatorId: Int) {
        operatorRepository.deleteById(operatorId)
    }
}
