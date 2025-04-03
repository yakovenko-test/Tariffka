package code.yakovenko.tariffka.domain.model

import code.yakovenko.tariffka.data.local.entity.OperatorEntity
import code.yakovenko.tariffka.domain.model.utils.IdType
import java.net.URL

data class Operator(
    val id: IdType,
    val name: String,
    val url: URL,
    val description: String,
    val country: String,
    val yearOfFoundation: Int,
    val averageRating: Double,
)

fun Operator.toData() = OperatorEntity(id.id)
