package test.yakovenko.data.mapping.tariff_feedback

import test.yakovenko.tariffka.core.Mapper
import test.yakovenko.tariffka.data.model.TariffFeedbackEntity
import test.yakovenko.tariffka.domain.model.TariffFeedback

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
