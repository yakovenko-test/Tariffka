package test.yakovenko.domain.model.type

import test.yakovenko.domain.validation.validate
import test.yakovenko.domain.validation.validator.AverageEstimationValidator

@JvmInline
value class AverageEstimation(val value: Double) {
    init {
        validate { this@AverageEstimation must AverageEstimationValidator() }
    }

    companion object {
        val min = Estimation.lowest.value
        val max = Estimation.highest.value

        val range = min.toDouble()..max.toDouble()
    }
}
