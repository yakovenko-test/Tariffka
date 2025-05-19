package validation.validator

import validation.ValidationResult

interface Validator<T> {
    operator fun invoke(target: T): ValidationResult
}
