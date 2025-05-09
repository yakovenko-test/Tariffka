package code.yakovenko.domain.model

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

    }
}
