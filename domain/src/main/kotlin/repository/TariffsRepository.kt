package repository

import model.Tariff
import java.util.*

interface TariffsRepository {
    suspend fun create(tariff: Tariff): Tariff?
    suspend fun read(tariffId: UUID): Tariff?
    suspend fun update(tariff: Tariff): Tariff?
    suspend fun delete(tariffId: UUID): Boolean

    suspend fun readAll(): Collection<Tariff>

    suspend fun findByOperator(operatorId: UUID): Collection<Tariff>

    suspend fun exists(tariffId: UUID): Boolean
}
