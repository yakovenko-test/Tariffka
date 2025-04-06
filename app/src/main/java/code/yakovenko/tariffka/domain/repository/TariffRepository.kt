package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.Tariff
import kotlinx.coroutines.flow.Flow

interface TariffRepository {
    suspend fun create(tariff: Tariff)

    suspend fun readById(tariffId: Int): Tariff?
    suspend fun readAll(): Flow<List<Tariff>>

    suspend fun update(tariff: Tariff)

    suspend fun deleteById(tariffId: Int)
}
