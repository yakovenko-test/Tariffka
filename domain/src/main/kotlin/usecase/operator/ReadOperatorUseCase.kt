package usecase.operator

import exception.ModelNotFoundException
import exception.ModelReadException
import repository.OperatorsRepository
import java.util.*
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