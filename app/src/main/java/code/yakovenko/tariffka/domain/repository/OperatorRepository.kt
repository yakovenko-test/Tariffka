package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.Operator
import java.util.UUID

interface OperatorRepository {
    fun create(operator: Operator): Operator

    fun readById(operatorId: UUID): Operator?
    fun readAll(): Collection<Operator>

    fun update(operator: Operator): Operator?

    fun deleteById(operatorId: UUID)

    fun containsId(operatorId: UUID): Boolean
    fun notContainsId(operatorId: UUID) = !containsId(operatorId)
}
