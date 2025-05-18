package test.yakovenko.domain.usecase.discount

import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.exception.ModelUpdateException
import test.yakovenko.domain.model.Discount
import test.yakovenko.domain.repository.DiscountsRepository
import test.yakovenko.domain.repository.OperatorsRepository
import javax.inject.Inject

class UpdateDiscountUseCase @Inject constructor(
    private val discountsRepository: DiscountsRepository,
    private val operatorsRepository: OperatorsRepository,
) {
    suspend operator fun invoke(discount: Discount) = runCatching {
        if (!discountsRepository.exists(discount.id)) {
            throw ModelNotFoundException("Discount", discount.id)
        }

        if (!operatorsRepository.exists(discount.operatorId)) {
            throw ModelNotFoundException("Operator", discount.operatorId)
        }

        discountsRepository.update(discount)
            ?: throw ModelUpdateException("Discount", discount.id)
    }
}