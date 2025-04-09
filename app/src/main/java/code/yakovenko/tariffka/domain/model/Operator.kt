package code.yakovenko.tariffka.domain.model

import java.time.Year

data class Operator(
    val id: Int,
    val name: String,
    val url: String?,
    val description: String?,
    val yearOfFoundation: Int?,
) {
    init {
        require(name.isNotBlank())
        require(description?.isNotBlank() != false)
        require(yearOfFoundation in 0..Year.now().value)
    }
}
