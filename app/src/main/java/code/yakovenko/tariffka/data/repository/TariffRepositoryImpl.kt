package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.data.local.dao.TariffDao
import code.yakovenko.tariffka.data.local.entity.toDomain
import code.yakovenko.tariffka.domain.model.Tariff
import code.yakovenko.tariffka.domain.model.toData
import code.yakovenko.tariffka.domain.repository.TariffRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    override suspend fun readAll(): Flow<List<Tariff>> {
        return tariffDao.selectAll().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun update(tariff: Tariff) {
        tariffDao.update(tariff.toData())
    }

    override suspend fun deleteById(tariffId: IdType) {
        tariffDao.deleteById(tariffId)
    }
}
