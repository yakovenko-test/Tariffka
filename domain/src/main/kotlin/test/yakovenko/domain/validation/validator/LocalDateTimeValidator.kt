package test.yakovenko.domain.validation.validator

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import test.yakovenko.common.Relation
import test.yakovenko.common.extension.now
import test.yakovenko.domain.validation.ValidationResult

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
