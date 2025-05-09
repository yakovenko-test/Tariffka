package code.yakovenko.domain.repository

import code.yakovenko.domain.model.Tariff
import java.util.UUID

interface TariffsRepository {
    fun create(tariff: Tariff): Tariff

    fun readById(tariffId: UUID): Tariff?
    fun readByOperatorId(operatorId: UUID): Collection<Tariff>
    fun readAll(): Collection<Tariff>

    fun update(tariff: Tariff): Tariff?

    fun deleteById(tariffId: UUID)

    fun containsId(tariffId: UUID): Boolean
    fun notContainsId(tariffId: UUID) = !containsId(tariffId)
}
