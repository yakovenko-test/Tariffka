package code.yakovenko.tariffka.domain.model

import code.yakovenko.tariffka.data.local.entity.TariffFeedbackEntity
import java.time.LocalDateTime

data class TariffFeedback(
    val id: IdType,
    val description: String?,
    val rating: Int,
    val publishedAt: LocalDateTime,
    val tariff: Tariff,
    val user: User,
) {
    init {
        require(rating in 0..5)
        require(publishedAt <= LocalDateTime.now())
    }
}

fun TariffFeedback.toData() = TariffFeedbackEntity(id.id)
