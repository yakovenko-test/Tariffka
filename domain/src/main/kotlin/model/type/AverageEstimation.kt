package model.type

import validation.validate
import validation.validator.AverageEstimationValidator

@JvmInline
value class AverageEstimation(val value: Double) {
    init {
        validate { this@AverageEstimation must AverageEstimationValidator() }
    }

    companion object {
        val min = Estimation.Companion.lowest.value
        val max = Estimation.Companion.highest.value

        val range = min.toDouble()..max.toDouble()
    }
}
