package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.Operator
import kotlinx.coroutines.flow.Flow

interface OperatorRepository {
    suspend fun create(operator: Operator)

    fun readById(operatorId: Int): Flow<Operator?>
    fun readAll(): Flow<List<Operator>>

    suspend fun update(operator: Operator)

    suspend fun deleteById(operatorId: Int)
}
