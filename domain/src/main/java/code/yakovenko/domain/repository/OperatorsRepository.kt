package code.yakovenko.domain.repository

import code.yakovenko.domain.model.Operator
import java.util.UUID

interface OperatorsRepository {
    fun create(operator: Operator): Operator

    fun readById(operatorId: UUID): Operator?
    fun readAll(): Collection<Operator>

    fun update(operator: Operator): Operator?

    fun deleteById(operatorId: UUID)

    fun containsId(operatorId: UUID): Boolean
    fun notContainsId(operatorId: UUID) = !containsId(operatorId)
}
