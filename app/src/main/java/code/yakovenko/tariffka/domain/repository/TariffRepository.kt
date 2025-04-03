package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.Tariff

interface TariffRepository {
    suspend fun getAll(): List<Tariff>
}
