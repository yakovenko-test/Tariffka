package usecase.operator

import exception.ModelDeleteException
import exception.ModelNotFoundException
import repository.OperatorsRepository
import java.util.*
import javax.inject.Inject

class DeleteOperatorUseCase @Inject constructor(
    private val operatorsRepository: OperatorsRepository,
) {
    suspend operator fun invoke(operatorId: UUID) = runCatching {
        if (!operatorsRepository.exists(operatorId)) {
            throw ModelNotFoundException("Operator", operatorId)
        }

        if (!operatorsRepository.delete(operatorId)) {
            throw ModelDeleteException("Operator", operatorId)
        }
    }
}