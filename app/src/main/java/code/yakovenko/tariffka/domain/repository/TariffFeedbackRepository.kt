package code.yakovenko.tariffka.domain.repository

import code.yakovenko.tariffka.domain.model.TariffFeedback

interface TariffFeedbackRepository {
    suspend fun getAll(): List<TariffFeedback>
}
