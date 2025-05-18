package test.yakovenko.domain.usecase.operator

import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.exception.ModelReadException
import test.yakovenko.domain.repository.OperatorsRepository
import java.util.UUID
import javax.inject.Inject

class ReadOperatorUseCase @Inject constructor(
    private val operatorsRepository: OperatorsRepository,
) {
    suspend operator fun invoke(operatorId: UUID) = runCatching {
        if (!operatorsRepository.exists(operatorId)) {
            throw ModelNotFoundException("Operator", operatorId)
        }

        operatorsRepository.read(operatorId)
            ?: throw ModelReadException("Operator", operatorId)
    }
}