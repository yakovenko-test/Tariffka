package code.yakovenko.tariffka.domain.usecase.delete

import code.yakovenko.tariffka.domain.exception.ServiceNotFoundException
import code.yakovenko.tariffka.domain.repository.ServiceRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class DeleteServiceUseCase @Inject constructor(
    private val serviceRepository: ServiceRepository
) {
    suspend operator fun invoke(serviceId: Uuid) {
        if (serviceRepository.readById(serviceId).firstOrNull() == null) {
            throw ServiceNotFoundException(serviceId)
        }

        serviceRepository.deleteById(serviceId)
    }
}
