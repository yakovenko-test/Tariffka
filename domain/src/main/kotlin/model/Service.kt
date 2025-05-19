package model

import model.type.AverageEstimation
import model.type.PaymentPlan
import validation.validate
import validation.validator.StringFieldValidator
import java.net.URL
import java.util.*

data class Service(
    val id: UUID = UUID.randomUUID(),
    val operatorId: UUID,
    val name: String,
    val description: String,
    val url: URL,
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
