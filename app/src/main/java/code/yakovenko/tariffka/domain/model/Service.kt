package code.yakovenko.tariffka.domain.model

data class Service(
    val id: Int,
    val operatorId: Int,
    val name: String,
    val cost: Int,
    val description: String,
) {
    init {
        require(name.isNotBlank())
        require(cost >= 0)
        require(description.isNotBlank())
    }
}
