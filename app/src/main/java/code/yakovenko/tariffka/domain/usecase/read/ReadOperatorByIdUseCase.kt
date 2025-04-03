package code.yakovenko.tariffka.domain.usecase.read

import code.yakovenko.tariffka.domain.model.utils.IdType
import code.yakovenko.tariffka.domain.repository.OperatorRepository
import javax.inject.Inject

class ReadOperatorByIdUseCase @Inject constructor(
    private val operatorRepository: OperatorRepository
) {
    suspend operator fun invoke(operatorId: IdType) = operatorRepository.readById(operatorId)
}
