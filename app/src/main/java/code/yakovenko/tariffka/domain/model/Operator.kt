package code.yakovenko.tariffka.domain.model

import android.util.Patterns
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
        require(url?.let { Patterns.WEB_URL.matcher(it).matches() } != false)
        require(description?.isNotBlank() != false)
        require(yearOfFoundation in 0..Year.now().value)
    }
}
