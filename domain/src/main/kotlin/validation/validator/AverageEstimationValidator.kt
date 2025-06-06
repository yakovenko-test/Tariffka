package validation.validator

import model.type.AverageEstimation
import validation.ValidationResult

class AverageEstimationValidator : Validator<AverageEstimation> {
    override fun invoke(target: AverageEstimation): ValidationResult {
        return if (target.value in AverageEstimation.range) {
            ValidationResult.Valid
        } else {
            ValidationResult.Invalid(
                "Average estimation must be in range ${AverageEstimation.range}"
            )
        }
    }
}
