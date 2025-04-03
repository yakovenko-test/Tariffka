package code.yakovenko.tariffka.domain.model

import code.yakovenko.tariffka.data.local.entity.TariffFeedbackEntity
import code.yakovenko.tariffka.domain.model.utils.IdType
import java.time.LocalDateTime

data class TariffFeedback(
    val id: IdType,
    val description: String?,
    val rating: Int,
    val publishedAt: LocalDateTime,
    val tariffId: IdType,
    val userId: IdType,
)

fun TariffFeedback.toData() = TariffFeedbackEntity(id.id)
