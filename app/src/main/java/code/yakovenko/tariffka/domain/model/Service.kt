package code.yakovenko.tariffka.domain.model

import code.yakovenko.tariffka.domain.type.Currency
import code.yakovenko.tariffka.common.validation.StringFieldValidator
import code.yakovenko.tariffka.common.validation.validate
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
        validate(name, "Name", StringFieldValidator)
        validate(description, "Description", StringFieldValidator)
        TODO("currency == null if cost == 0")
    }
}
