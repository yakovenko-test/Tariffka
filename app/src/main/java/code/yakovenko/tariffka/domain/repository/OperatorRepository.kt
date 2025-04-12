package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.Operator
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
interface OperatorRepository {
    suspend fun create(operator: Operator)

    fun readById(operatorId: Uuid): Flow<Operator?>
    fun readAll(): Flow<List<Operator>>

    suspend fun update(operator: Operator)

    suspend fun deleteById(operatorId: Uuid)
}
