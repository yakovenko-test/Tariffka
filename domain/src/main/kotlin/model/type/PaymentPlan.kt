package model.type

import Relation
import validation.validate
import validation.validator.PairRelationValidator
import java.math.BigDecimal

sealed class PaymentPlan {
    data object Free : PaymentPlan()

    data class Purchase(
        val price: BigDecimal,
        val currency: Currency,
    ) : PaymentPlan() {
        init {
            validate {
                (price to BigDecimal(0)) must PairRelationValidator("Price", "Zero", Relation.GT)
            }
        }
    }

    data class Subscription(
        val price: BigDecimal,
        val currency: Currency,
        val period: Period,
    ) : PaymentPlan() {
        init {
            validate {
                (price to BigDecimal(0)) must PairRelationValidator("Price", "Zero", Relation.GT)
            }
        }
    }
}
