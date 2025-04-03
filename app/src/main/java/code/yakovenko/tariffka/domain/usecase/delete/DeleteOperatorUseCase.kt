package code.yakovenko.tariffka.domain.usecase.delete

import code.yakovenko.tariffka.domain.repository.OperatorRepository
import javax.inject.Inject

class DeleteOperatorUseCase @Inject constructor(
    private val operatorRepository: OperatorRepository
) {
    suspend operator fun invoke(operatorId: IdType) {
        operatorRepository.deleteById(operatorId)
    }
}
