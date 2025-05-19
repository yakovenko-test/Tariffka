package validation.validator

import Relation
import extension.now
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import validation.ValidationResult

class LocalDateTimeValidator(
    private val fieldName: String,
    private val timeZone: TimeZone,
) : Validator<LocalDateTime> {
    override operator fun invoke(target: LocalDateTime): ValidationResult {
        val now = LocalDateTime.now(timeZone)

        return PairRelationValidator<LocalDateTime>(
            fieldName, "CurrentDateTime ($now)", Relation.LE
        )(target to now)
    }
}
