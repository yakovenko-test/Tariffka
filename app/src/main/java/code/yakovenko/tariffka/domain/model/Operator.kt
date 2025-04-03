package code.yakovenko.tariffka.domain.model

import java.net.URL

data class Operator(
    val id: Long,
    val name: String,
    val url: URL,
    val description: String,
    val country: String,
    val yearOfFoundation: Int,
    var averageRating: Double,
)
