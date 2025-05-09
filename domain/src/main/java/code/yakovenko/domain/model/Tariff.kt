package code.yakovenko.domain.model

import code.yakovenko.domain.type.Currency
import java.util.UUID

data class Tariff(
    val id: UUID = UUID.randomUUID(),
    val operatorId: UUID,
    val name: String,
    val description: String,
    val cost: UInt?,
    val currency: Currency?,
    val minutesCount: UInt?,
    val gigabytesCount: UInt?,
    val averageEstimation: Double?,
) {
    init {

    }
}
