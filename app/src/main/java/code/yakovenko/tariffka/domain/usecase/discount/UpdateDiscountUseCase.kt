package code.yakovenko.tariffka.domain.usecase.discount

import code.yakovenko.tariffka.domain.exception.ModelNotFoundException
import code.yakovenko.tariffka.domain.model.Discount
import code.yakovenko.tariffka.domain.repository.DiscountRepository
import code.yakovenko.tariffka.domain.repository.OperatorRepository
import javax.inject.Inject

class UpdateDiscountUseCase @Inject constructor(
    private val discountRepository: DiscountRepository,
    private val operatorRepository: OperatorRepository
) {
    operator fun invoke(discount: Discount): Discount? {
        if (operatorRepository.notContainsId(discount.operatorId)) {
            throw ModelNotFoundException("Operator", discount.operatorId)
        }

        if (discountRepository.notContainsId(discount.id)) {
            throw ModelNotFoundException("Discount", discount.id)
        }

        return discountRepository.update(discount)
    }
}