package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.Operator
import code.yakovenko.tariffka.domain.model.utils.IdType

interface OperatorRepository {
    suspend fun create(operator: Operator)

    suspend fun readById(operatorId: IdType): Operator?
    suspend fun readAll(): List<Operator>

    suspend fun update(operator: Operator)

    suspend fun deleteById(operatorId: IdType)
}
