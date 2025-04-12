package code.yakovenko.tariffka.domain.usecase.update

import code.yakovenko.tariffka.domain.exception.OperatorNotFoundException
import code.yakovenko.tariffka.domain.model.Service
import code.yakovenko.tariffka.domain.repository.OperatorRepository
import code.yakovenko.tariffka.domain.repository.ServiceRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class UpdateServiceUseCase @Inject constructor(
    private val serviceRepository: ServiceRepository,
    private val operatorRepository: OperatorRepository
) {
    suspend operator fun invoke(service: Service) {
        if (operatorRepository.readById(service.operatorId).firstOrNull() == null) {
            throw OperatorNotFoundException(service.operatorId)
        }

        serviceRepository.update(service)
    }
}
