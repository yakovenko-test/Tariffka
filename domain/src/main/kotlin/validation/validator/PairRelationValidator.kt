package validation.validator

import Relation
import validation.ValidationResult

class PairRelationValidator<T : Comparable<T>>(
    private val firstFieldName: String,
    private val secondFieldName: String,
    private val relation: Relation
) : Validator<Pair<T, T>> {
    override fun invoke(target: Pair<T, T>): ValidationResult {
        return if (relation.check(target.first, target.second)) {
            ValidationResult.Valid
        } else {
            ValidationResult.Invalid(
                "$firstFieldName must be ${relation.description} $secondFieldName"
            )
        }
    }
}
