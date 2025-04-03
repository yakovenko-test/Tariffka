package code.yakovenko.tariffka.domain.model

import code.yakovenko.tariffka.data.local.entity.OptionEntity
import code.yakovenko.tariffka.domain.model.utils.IdType

data class Option(
    val id: IdType,
    val name: String,
    val cost: Int,
    val description: String,
    val operatorId: IdType,
)

fun Option.toData() = OptionEntity(id.id)
