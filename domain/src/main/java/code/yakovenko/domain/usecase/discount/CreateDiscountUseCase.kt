package code.yakovenko.domain.usecase.discount

import code.yakovenko.domain.exception.ModelDuplicateException
import code.yakovenko.domain.exception.ModelNotFoundException
import code.yakovenko.domain.model.Discount
import code.yakovenko.domain.repository.DiscountsRepository
import code.yakovenko.domain.repository.OperatorsRepository
import javax.inject.Inject

class CreateDiscountUseCase @Inject constructor(
    private val discountsRepository: DiscountsRepository,
    private val operatorsRepository: OperatorsRepository
) {
    operator fun invoke(discount: Discount): Discount {
        if (operatorsRepository.notContainsId(discount.operatorId)) {
            throw ModelNotFoundException("Operator", discount.operatorId)
        }

        if (discountsRepository.containsId(discount.id)) {
            throw ModelDuplicateException("Discount", discount.id)
        }

        return discountsRepository.create(discount)
    }
}