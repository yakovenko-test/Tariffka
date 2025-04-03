package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.Tariff
import code.yakovenko.tariffka.domain.model.utils.IdType

interface TariffRepository {
    suspend fun create(tariff: Tariff)

    suspend fun readById(tariffId: IdType): Tariff?
    suspend fun readAll(): List<Tariff>

    suspend fun update(tariff: Tariff)

    suspend fun deleteById(tariffId: IdType)
}
