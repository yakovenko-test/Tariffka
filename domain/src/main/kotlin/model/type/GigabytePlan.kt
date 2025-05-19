package model.type

import Relation
import validation.validate
import validation.validator.PairRelationValidator

sealed class GigabytePlan {
    data object NotIncluded : GigabytePlan()

    data object Unlimited : GigabytePlan()

    data class Limited(val count: UInt) : GigabytePlan() {
        init {
            validate {
                (count to 0U) must PairRelationValidator("GigabytesCount", "Zero", Relation.GT)
            }
        }
    }
}
