package test.yakovenko.domain.validation.validator

import test.yakovenko.domain.validation.ValidationResult

interface Validator<T> {
    operator fun invoke(target: T): ValidationResult
}
