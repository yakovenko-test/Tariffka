package code.yakovenko.tariffka.domain.model

import code.yakovenko.tariffka.common.validation.StringFieldValidator
import code.yakovenko.tariffka.common.validation.WebURLValidator
import code.yakovenko.tariffka.common.validation.validate
import java.util.UUID

data class Operator(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val url: String?,
) {
    init {
        validate(name, "Name", StringFieldValidator)
        validate(url, "Url", WebURLValidator)
    }
}
