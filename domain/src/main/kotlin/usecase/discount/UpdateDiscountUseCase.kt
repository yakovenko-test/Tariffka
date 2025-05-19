package usecase.discount

import exception.ModelNotFoundException
import exception.ModelUpdateException
import model.Discount
import repository.DiscountsRepository
import repository.OperatorsRepository
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