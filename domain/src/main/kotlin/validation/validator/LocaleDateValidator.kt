package validation.validator

import Relation
import extension.now
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import validation.ValidationResult

class LocaleDateValidator(
    private val fieldName: String,
    private val timeZone: TimeZone,
) : Validator<LocalDate> {
    override operator fun invoke(target: LocalDate): ValidationResult {
        val now = LocalDate.now(timeZone)

        return PairRelationValidator<LocalDate>(
            fieldName, "CurrentDate ($now)", Relation.LE
        )(target to now)
    }
}
