package code.yakovenko.tariffka.domain.usecase.delete

import code.yakovenko.tariffka.domain.exception.OperatorNotFoundException
import code.yakovenko.tariffka.domain.repository.OperatorRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class DeleteOperatorUseCase @Inject constructor(
    private val operatorRepository: OperatorRepository
) {
    suspend operator fun invoke(operatorId: Uuid) {
        if (operatorRepository.readById(operatorId).firstOrNull() == null) {
            throw OperatorNotFoundException(operatorId)
        }

        operatorRepository.deleteById(operatorId)
    }
}
