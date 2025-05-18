package test.yakovenko.domain.model

import test.yakovenko.domain.model.type.AverageEstimation
import test.yakovenko.domain.model.type.GigabytePlan
import test.yakovenko.domain.model.type.MinutePlan
import test.yakovenko.domain.model.type.PaymentPlan
import test.yakovenko.domain.model.type.SmsPlan
import test.yakovenko.domain.validation.validate
import test.yakovenko.domain.validation.validator.StringFieldValidator
import java.net.URL
import java.util.UUID

data class Tariff(
    val id: UUID = UUID.randomUUID(),
    val operatorId: UUID,
    val name: String,
    val description: String,
    val url: URL,
    val minutePlan: MinutePlan,
    val gigabytePlan: GigabytePlan,
    val smsPlan: SmsPlan,
    val paymentPlan: PaymentPlan,
    val averageEstimation: AverageEstimation?,
) {
    init {
        validate {
            name must StringFieldValidator("Name")
            description must StringFieldValidator("Description")
        }
    }
}
