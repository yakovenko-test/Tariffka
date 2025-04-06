package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.data.local.dao.TariffDao
import code.yakovenko.tariffka.data.mapping.TariffMapper
import code.yakovenko.tariffka.domain.model.Tariff
import code.yakovenko.tariffka.domain.repository.TariffRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TariffRepositoryImpl @Inject constructor(
    private val tariffDao: TariffDao
) : TariffRepository {
    override suspend fun create(tariff: Tariff) {
        tariffDao.insert(TariffMapper.toData(tariff))
    }

    override suspend fun readById(tariffId: Int): Tariff? {
        return tariffDao.selectById(tariffId)?.let {
            TariffMapper.toDomain(it)
        }
    }

    override suspend fun readAll(): Flow<List<Tariff>> {
        return tariffDao.selectAll().map { entities ->
            entities.map { TariffMapper.toDomain(it) }
        }
    }

    override suspend fun update(tariff: Tariff) {
        tariffDao.update(TariffMapper.toData(tariff))
    }

    override suspend fun deleteById(tariffId: Int) {
        tariffDao.deleteById(tariffId)
    }
}
