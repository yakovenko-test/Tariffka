package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.data.local.dao.TariffFeedbackDao
import code.yakovenko.tariffka.data.local.entity.toDomain
import code.yakovenko.tariffka.domain.model.TariffFeedback
import code.yakovenko.tariffka.domain.model.toData
import code.yakovenko.tariffka.domain.repository.TariffFeedbackRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TariffFeedbackRepositoryImpl @Inject constructor(
    private val tariffFeedbackDao: TariffFeedbackDao
) : TariffFeedbackRepository {
    override suspend fun create(tariffFeedback: TariffFeedback) {
        tariffFeedbackDao.insert(tariffFeedback.toData())
    }

    override suspend fun readById(tariffFeedbackId: IdType): TariffFeedback? {
        return tariffFeedbackDao.selectById(tariffFeedbackId)?.toDomain()
    }

    override suspend fun readAll(): Flow<List<TariffFeedback>> {
        return tariffFeedbackDao.selectAll().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun update(tariffFeedback: TariffFeedback) {
        tariffFeedbackDao.update(tariffFeedback.toData())
    }

    override suspend fun deleteById(tariffFeedbackId: IdType) {
        tariffFeedbackDao.deleteById(tariffFeedbackId)
    }
}
