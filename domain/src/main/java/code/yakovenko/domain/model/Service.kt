package code.yakovenko.domain.model

import code.yakovenko.domain.type.Currency
import java.util.UUID

data class Service(
    val id: UUID = UUID.randomUUID(),
    val operatorId: UUID,
    val name: String,
    val description: String,
    val cost: UInt,
    val currency: Currency?
) {
    init {
        TODO("currency == null if cost == 0")
    }
}
