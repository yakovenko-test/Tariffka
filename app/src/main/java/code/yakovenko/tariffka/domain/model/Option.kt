package code.yakovenko.tariffka.domain.model

data class Option(
    val id: Long,
    val name: String,
    val cost: Int,
    val description: String,
    val operator: Operator,
)
