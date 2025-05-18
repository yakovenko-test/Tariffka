package test.yakovenko.domain.model.type

import test.yakovenko.common.Relation
import test.yakovenko.domain.validation.validate
import test.yakovenko.domain.validation.validator.PairRelationValidator

sealed class SmsPlan {
    data object NotIncluded : SmsPlan()

    data object Unlimited : SmsPlan()

    data class Limited(val count: UInt) : SmsPlan() {
        init {
            validate {
                (count to 0U) must PairRelationValidator("SmsCount", "Zero", Relation.GT)
            }
        }
    }
}
