package code.yakovenko.tariffka.domain.model

import android.util.Patterns
import code.yakovenko.tariffka.data.local.entity.OperatorEntity
import java.time.Year

data class Operator(
    val id: Long,
    val name: String,
    val url: String,
    val description: String,
    val yearOfFoundation: Int,
    val averageRating: Double = 0.0,
) {
    init {
        require(Patterns.WEB_URL.matcher(url).matches())
        require(yearOfFoundation in 0..Year.now().value)
        require(averageRating in 0.0..5.0)
    }
}

fun Operator.toData() = OperatorEntity(
    id,
    name,
    url,
    description,
    yearOfFoundation,
    averageRating
)
