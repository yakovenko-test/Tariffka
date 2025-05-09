package code.yakovenko.data.mapping.tariff_feedback

import code.yakovenko.tariffka.core.Mapper
import code.yakovenko.tariffka.data.model.TariffFeedbackEntity
import code.yakovenko.tariffka.domain.model.TariffFeedback

class TariffFeedbackEntityToDomainMapper : Mapper<TariffFeedbackEntity, TariffFeedback> {
    override fun transform(from: TariffFeedbackEntity): TariffFeedback {
        return TariffFeedback(
            id = from.id.value,
            authorId = from.authorId.value,
            tariffId = from.tariffId.value,
            description = from.description,
            estimation = from.estimation,
            publishedAt = from.publishedAt
        )
    }
}
