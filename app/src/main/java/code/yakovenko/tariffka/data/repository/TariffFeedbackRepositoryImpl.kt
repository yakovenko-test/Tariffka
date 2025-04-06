package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.data.local.dao.TariffFeedbackDao
import code.yakovenko.tariffka.data.mapping.TariffFeedbackMapper
import code.yakovenko.tariffka.domain.model.TariffFeedback
import code.yakovenko.tariffka.domain.repository.TariffFeedbackRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TariffFeedbackRepositoryImpl @Inject constructor(
    private val tariffFeedbackDao: TariffFeedbackDao
) : TariffFeedbackRepository {
    override suspend fun create(tariffFeedback: TariffFeedback) {
        tariffFeedbackDao.insert(TariffFeedbackMapper.toData(tariffFeedback))
    }

    override suspend fun readById(tariffFeedbackId: Int): TariffFeedback? {
        return tariffFeedbackDao.selectById(tariffFeedbackId)?.let {
            TariffFeedbackMapper.toDomain(it)
        }
    }

    override suspend fun readAll(): Flow<List<TariffFeedback>> {
        return tariffFeedbackDao.selectAll().map { entities ->
            entities.map { TariffFeedbackMapper.toDomain(it) }
        }
    }

    override suspend fun update(tariffFeedback: TariffFeedback) {
        tariffFeedbackDao.update(TariffFeedbackMapper.toData(tariffFeedback))
    }

    override suspend fun deleteById(tariffFeedbackId: Int) {
        tariffFeedbackDao.deleteById(tariffFeedbackId)
    }
}
