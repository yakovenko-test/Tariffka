package code.yakovenko.tariffka.domain.model

import code.yakovenko.tariffka.core.enums.Currency
import code.yakovenko.tariffka.core.validation.EstimationValidator
import code.yakovenko.tariffka.core.validation.StringFieldValidator
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class Tariff @OptIn(ExperimentalUuidApi::class) constructor(
    val id: Uuid = Uuid.random(),
    val operatorId: Uuid,
    val name: String,
    val description: String,
    val cost: UInt,
    val currency: Currency,
    val minutesCount: UInt,
    val gigabytesCount: UInt,
    val averageEstimation: Double,
) {
    init {
        require(StringFieldValidator(name, "Name")) {
            StringFieldValidator.errorMessages.joinToString()
        }
        require(StringFieldValidator(description, "Description")) {
            StringFieldValidator.errorMessages.joinToString()
        }
        require(EstimationValidator(averageEstimation, "AverageEstimation")) {
            EstimationValidator.errorMessages.joinToString()
        }
    }
}
