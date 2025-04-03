package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.Operator
import kotlinx.coroutines.flow.Flow

interface OperatorRepository {
    suspend fun create(operator: Operator)

    suspend fun readById(operatorId: IdType): Operator?
    suspend fun readAll(): Flow<List<Operator>>

    suspend fun update(operator: Operator)

    suspend fun deleteById(operatorId: IdType)
}
