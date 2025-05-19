package model

import Relation
import kotlinx.datetime.LocalDate
import validation.validate
import validation.validator.PairRelationValidator
import validation.validator.StringFieldValidator
import java.net.URL
import java.util.*

data class Discount(
    val id: UUID = UUID.randomUUID(),
    val operatorId: UUID,
    val title: String,
    val description: String,
    val url: URL,
    val activeFrom: LocalDate,
    val activeUntil: LocalDate,
) {
    init {
        validate {
            title must StringFieldValidator("Title")
            description must StringFieldValidator("Description")

            (activeFrom to activeUntil) must PairRelationValidator(
                "ActiveFrom", "ActiveUntil", Relation.LT
            )
        }
    }
}
