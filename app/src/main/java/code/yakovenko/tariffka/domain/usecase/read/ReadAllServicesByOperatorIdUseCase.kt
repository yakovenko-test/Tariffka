package code.yakovenko.tariffka.domain.usecase.read

import code.yakovenko.tariffka.domain.exception.OperatorNotFoundException
import code.yakovenko.tariffka.domain.model.Service
import code.yakovenko.tariffka.domain.repository.OperatorRepository
import code.yakovenko.tariffka.domain.repository.ServiceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class ReadAllServicesByOperatorIdUseCase @Inject constructor(
    private val serviceRepository: ServiceRepository,
    private val operatorRepository: OperatorRepository
) {
    suspend operator fun invoke(operatorId: Uuid): Flow<List<Service>> {
        if (operatorRepository.readById(operatorId).firstOrNull() == null) {
            throw OperatorNotFoundException(operatorId)
        }

        return serviceRepository.readByOperatorId(operatorId)
    }
}
