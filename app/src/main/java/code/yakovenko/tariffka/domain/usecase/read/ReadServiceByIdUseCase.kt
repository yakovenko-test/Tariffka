package code.yakovenko.tariffka.domain.usecase.read

import code.yakovenko.tariffka.domain.exception.ServiceNotFoundException
import code.yakovenko.tariffka.domain.model.Service
import code.yakovenko.tariffka.domain.repository.ServiceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class ReadServiceByIdUseCase @Inject constructor(
    private val serviceRepository: ServiceRepository
) {
    suspend operator fun invoke(serviceId: Uuid): Flow<Service?> {
        if (serviceRepository.readById(serviceId).firstOrNull() == null) {
            throw ServiceNotFoundException(serviceId)
        }

        return serviceRepository.readById(serviceId)
    }
}