package test.yakovenko.domain.validation.validator

import test.yakovenko.domain.validation.ValidationResult

class NullableValidator<T>(
    private val validator: Validator<T>,
) : Validator<T?> {
    override fun invoke(target: T?): ValidationResult {
        return target?.let { validator(target) } ?: ValidationResult.Valid
    }
}
