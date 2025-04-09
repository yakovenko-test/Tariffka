package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.data.local.dao.TariffDao
import code.yakovenko.tariffka.data.mapping.TariffMapper
import code.yakovenko.tariffka.domain.model.Tariff
import code.yakovenko.tariffka.domain.repository.TariffRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TariffRepositoryImpl(
    private val tariffDao: TariffDao
) : TariffRepository {
    override suspend fun create(tariff: Tariff) {
        tariffDao.insertTariff(TariffMapper.toData(tariff))
    }

    override fun readById(tariffId: Int): Flow<Tariff?> {
        return tariffDao.selectTariffById(tariffId).map { entity ->
            entity?.let { TariffMapper.toDomain(it) }
        }
    }

    override fun readByOperatorId(operatorId: Int): Flow<List<Tariff>> {
        return tariffDao.selectTariffsByOperatorId(operatorId).map { entities ->
            entities.map { TariffMapper.toDomain(it) }
        }
    }

    override fun readAll(): Flow<List<Tariff>> {
        return tariffDao.selectAllTariffs().map { entities ->
            entities.map { TariffMapper.toDomain(it) }
        }
    }

    override suspend fun update(tariff: Tariff) {
        tariffDao.updateTariff(TariffMapper.toData(tariff))
    }

    override suspend fun deleteById(tariffId: Int) {
        tariffDao.deleteTariffById(tariffId)
    }
}
