package code.yakovenko.tariffka.domain.exception

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class ServiceNotFoundException(serviceId: Uuid) :
    RuntimeException("Service with id $serviceId not found")
