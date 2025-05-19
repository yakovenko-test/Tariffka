package model

import model.type.*
import validation.validate
import validation.validator.StringFieldValidator
import java.net.URL
import java.util.*

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
