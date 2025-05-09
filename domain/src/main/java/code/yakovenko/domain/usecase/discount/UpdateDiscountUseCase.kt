package code.yakovenko.domain.usecase.discount

import code.yakovenko.domain.exception.ModelNotFoundException
import code.yakovenko.domain.model.Discount
import code.yakovenko.domain.repository.DiscountsRepository
import code.yakovenko.domain.repository.OperatorsRepository
import javax.inject.Inject

class UpdateDiscountUseCase @Inject constructor(
    private val discountsRepository: DiscountsRepository,
    private val operatorsRepository: OperatorsRepository
) {
    operator fun invoke(discount: Discount): Discount? {
        if (operatorsRepository.notContainsId(discount.operatorId)) {
            throw ModelNotFoundException("Operator", discount.operatorId)
        }

        if (discountsRepository.notContainsId(discount.id)) {
            throw ModelNotFoundException("Discount", discount.id)
        }

        return discountsRepository.update(discount)
    }
}