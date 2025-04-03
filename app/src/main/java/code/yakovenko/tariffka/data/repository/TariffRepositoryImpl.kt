package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.data.local.dao.TariffDao
import code.yakovenko.tariffka.data.local.entity.toDomain
import code.yakovenko.tariffka.domain.model.Tariff
import code.yakovenko.tariffka.domain.model.toData
import code.yakovenko.tariffka.domain.model.utils.IdType
import code.yakovenko.tariffka.domain.repository.TariffRepository
import javax.inject.Inject

class TariffRepositoryImpl @Inject constructor(
    private val tariffDao: TariffDao
) : TariffRepository {
    override suspend fun create(tariff: Tariff) {
        tariffDao.insert(tariff.toData())
    }

    override suspend fun readById(tariffId: IdType): Tariff? {
        return tariffDao.selectById(tariffId)?.toDomain()
    }

    override suspend fun readAll(): List<Tariff> {
        return tariffDao.selectAll().map { it.toDomain() }
    }

    override suspend fun update(tariff: Tariff) {
        tariffDao.update(tariff.toData())
    }

    override suspend fun deleteById(tariffId: IdType) {
        tariffDao.deleteById(tariffId)
    }
}
