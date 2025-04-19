package code.yakovenko.tariffka.domain.model

import code.yakovenko.tariffka.common.validation.LocalDateTimeValidator
import code.yakovenko.tariffka.common.validation.StringFieldValidator
import code.yakovenko.tariffka.common.validation.validate
import kotlinx.datetime.LocalDateTime
import java.util.UUID

data class Discount(
    val id: UUID = UUID.randomUUID(),
    val operatorId: UUID,
    val title: String,
    val description: String,
    val activeFrom: LocalDateTime,
    val activeUntil: LocalDateTime?,
) {
    init {
        validate(title, "Title", StringFieldValidator)
        validate(description, "Description", StringFieldValidator)
    }
}
