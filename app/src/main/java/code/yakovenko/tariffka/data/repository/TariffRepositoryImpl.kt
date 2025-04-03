package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.data.local.dao.TariffDao
import code.yakovenko.tariffka.data.local.entity.toDomain
import code.yakovenko.tariffka.domain.model.Tariff
import code.yakovenko.tariffka.domain.repository.TariffRepository
import javax.inject.Inject

class TariffRepositoryImpl @Inject constructor(
    private val tariffDao: TariffDao
): TariffRepository {
    override suspend fun getAll(): List<Tariff> {
        return tariffDao.getAll().map { it.toDomain() }
    }
}
