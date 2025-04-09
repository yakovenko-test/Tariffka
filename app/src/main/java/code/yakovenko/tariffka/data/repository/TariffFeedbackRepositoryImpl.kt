package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.data.local.dao.TariffFeedbackDao
import code.yakovenko.tariffka.data.mapping.TariffFeedbackMapper
import code.yakovenko.tariffka.domain.model.TariffFeedback
import code.yakovenko.tariffka.domain.repository.TariffFeedbackRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TariffFeedbackRepositoryImpl(
    private val tariffFeedbackDao: TariffFeedbackDao
) : TariffFeedbackRepository {
    override suspend fun create(tariffFeedback: TariffFeedback) {
        tariffFeedbackDao.insertTariffFeedback(TariffFeedbackMapper.toData(tariffFeedback))
    }

    override fun readById(tariffFeedbackId: Int): Flow<TariffFeedback?> {
        return tariffFeedbackDao.selectTariffFeedbackById(tariffFeedbackId).map { entity ->
            entity?.let { TariffFeedbackMapper.toDomain(it) }
        }
    }

    override fun readByTariffId(tariffId: Int): Flow<List<TariffFeedback>> {
        return tariffFeedbackDao.selectTariffFeedbacksByTariffId(tariffId).map { entities ->
            entities.map { TariffFeedbackMapper.toDomain(it) }
        }
    }

    override fun readAll(): Flow<List<TariffFeedback>> {
        return tariffFeedbackDao.selectAllTariffFeedbacks().map { entities ->
            entities.map { TariffFeedbackMapper.toDomain(it) }
        }
    }

    override suspend fun update(tariffFeedback: TariffFeedback) {
        tariffFeedbackDao.updateTariffFeedback(TariffFeedbackMapper.toData(tariffFeedback))
    }

    override suspend fun deleteById(tariffFeedbackId: Int) {
        tariffFeedbackDao.deleteTariffFeedbackById(tariffFeedbackId)
    }
}
