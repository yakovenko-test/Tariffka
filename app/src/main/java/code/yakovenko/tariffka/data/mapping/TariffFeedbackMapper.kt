package code.yakovenko.tariffka.data.mapping

import code.yakovenko.tariffka.data.local.entity.TariffFeedbackEntity
import code.yakovenko.tariffka.domain.model.TariffFeedback
import java.time.LocalDateTime

object TariffFeedbackMapper {
    fun toDomain(tariffFeedbackEntity: TariffFeedbackEntity): TariffFeedback {
        return TariffFeedback(
            id = tariffFeedbackEntity.id,
            tariffId = tariffFeedbackEntity.tariffId,
            userId = tariffFeedbackEntity.userId,
            description = tariffFeedbackEntity.description,
            rating = tariffFeedbackEntity.rating,
            publishedAt = tariffFeedbackEntity.publishedAt
        ).apply {
            require(description?.isNotBlank() == true)
            require(rating in 0..5)
            require(publishedAt <= LocalDateTime.now())
        }
    }

    fun toData(tariffFeedback: TariffFeedback): TariffFeedbackEntity {
        return TariffFeedbackEntity(
            id = tariffFeedback.id,
            tariffId = tariffFeedback.tariffId,
            userId = tariffFeedback.userId,
            description = tariffFeedback.description?.takeIf { it.isNotBlank() },
            rating = tariffFeedback.rating,
            publishedAt = tariffFeedback.publishedAt
        )
    }
}
