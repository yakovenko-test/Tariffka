package test.yakovenko.domain.repository

import test.yakovenko.domain.model.Operator
import java.util.UUID

interface OperatorsRepository {
    suspend fun create(operator: Operator): Operator?
    suspend fun read(operatorId: UUID): Operator?
    suspend fun update(operator: Operator): Operator?
    suspend fun delete(operatorId: UUID): Boolean

    suspend fun readAll(): Collection<Operator>

    suspend fun exists(operatorId: UUID): Boolean
}
