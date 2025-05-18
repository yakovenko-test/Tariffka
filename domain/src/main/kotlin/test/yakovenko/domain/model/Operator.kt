package test.yakovenko.domain.model

import test.yakovenko.domain.model.type.AverageEstimation
import test.yakovenko.domain.validation.validate
import test.yakovenko.domain.validation.validator.StringFieldValidator
import java.net.URL
import java.util.UUID

data class Operator(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val url: URL,
    val averageEstimation: AverageEstimation?,
) {
    init {
        validate {
            name must StringFieldValidator("Name")
        }
    }
}
