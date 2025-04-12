package code.yakovenko.tariffka.domain.exception

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class TariffNotFoundException(tariffId: Uuid) :
    RuntimeException("Tariff with id $tariffId not found")
