package code.yakovenko.tariffka.data.repository

import code.yakovenko.tariffka.data.local.dao.TariffFeedbackDao
import code.yakovenko.tariffka.data.local.entity.toDomain
import code.yakovenko.tariffka.domain.model.TariffFeedback
import code.yakovenko.tariffka.domain.repository.TariffFeedbackRepository
import javax.inject.Inject

class TariffFeedbackRepositoryImpl @Inject constructor(
    private val tariffFeedbackDao: TariffFeedbackDao
): TariffFeedbackRepository {
    override suspend fun getAll(): List<TariffFeedback> {
        return tariffFeedbackDao.getAll().map { it.toDomain() }
    }
}
