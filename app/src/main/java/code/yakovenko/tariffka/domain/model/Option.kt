package code.yakovenko.tariffka.domain.model

import code.yakovenko.tariffka.data.local.entity.OptionEntity

data class Option(
    val id: Long,
    val name: String,
    val cost: Int,
    val description: String,
    val operator: Operator,
) {
    init {
        require(cost > 0)
    }
}

fun Option.toData() = OptionEntity(
    id,
    name,
    cost,
    description,
    operator.id
)
