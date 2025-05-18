package test.yakovenko.domain.model.type

import test.yakovenko.common.Relation
import test.yakovenko.domain.validation.validate
import test.yakovenko.domain.validation.validator.PairRelationValidator

sealed class MinutePlan {
    data object NotIncluded : MinutePlan()

    data object Unlimited : MinutePlan()

    data class Limited(val count: UInt) : MinutePlan() {
        init {
            validate {
                (count to 0U) must PairRelationValidator("MinutesCount", "Zero", Relation.GT)
            }
        }
    }
}
