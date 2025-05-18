package test.yakovenko.domain.model

import kotlinx.datetime.LocalDate
import test.yakovenko.common.Relation
import test.yakovenko.domain.validation.validate
import test.yakovenko.domain.validation.validator.PairRelationValidator
import test.yakovenko.domain.validation.validator.StringFieldValidator
import java.net.URL
import java.util.UUID

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
