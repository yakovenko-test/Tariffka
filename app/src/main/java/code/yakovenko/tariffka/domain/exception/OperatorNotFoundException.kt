package code.yakovenko.tariffka.domain.exception

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class OperatorNotFoundException(operatorId: Uuid) :
    RuntimeException("Operator with id $operatorId not found")
