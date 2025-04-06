package code.yakovenko.tariffka.domain.model

import android.util.Patterns
import java.time.Year

data class Operator(
    val id: Int,
    val name: String,
    val url: String,
    val description: String,
    val yearOfFoundation: Int,
) {
    init {
        require(name.isNotBlank())
        require(Patterns.WEB_URL.matcher(url).matches())
        require(description.isNotBlank())
        require(yearOfFoundation in 0..Year.now().value)
    }
}
