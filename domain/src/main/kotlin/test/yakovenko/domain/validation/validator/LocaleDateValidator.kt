package test.yakovenko.domain.validation.validator

import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import test.yakovenko.common.Relation
import test.yakovenko.common.extension.now
import test.yakovenko.domain.validation.ValidationResult

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
