package model

import model.type.AverageEstimation
import validation.validate
import validation.validator.StringFieldValidator
import java.net.URL
import java.util.*

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
