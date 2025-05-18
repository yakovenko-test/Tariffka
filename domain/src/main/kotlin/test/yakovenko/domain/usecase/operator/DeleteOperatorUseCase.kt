package test.yakovenko.domain.usecase.operator

import test.yakovenko.domain.exception.ModelDeleteException
import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.repository.OperatorsRepository
import java.util.UUID
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