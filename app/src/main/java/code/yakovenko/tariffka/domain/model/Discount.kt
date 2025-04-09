package code.yakovenko.tariffka.domain.model

import java.time.LocalDateTime

data class Discount(
    val id: Int,
    val operatorId: Int,
    val name: String,
    val description: String,
    val activeFrom: LocalDateTime,
    val activeUntil: LocalDateTime,
) {
    init {
        require(name.isNotBlank())
        require(description.isNotBlank())
        require(activeFrom <= LocalDateTime.now())
        require(activeFrom <= activeUntil)
    }
}
