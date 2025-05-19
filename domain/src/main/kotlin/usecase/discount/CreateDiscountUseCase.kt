package usecase.discount

import exception.ModelCreateException
import exception.ModelDuplicateException
import exception.ModelNotFoundException
import model.Discount
import repository.DiscountsRepository
import repository.OperatorsRepository
import javax.inject.Inject

class CreateDiscountUseCase @Inject constructor(
    private val discountsRepository: DiscountsRepository,
    private val operatorsRepository: OperatorsRepository,
) {
    suspend operator fun invoke(discount: Discount) = runCatching {
        if (discountsRepository.exists(discount.id)) {
            throw ModelDuplicateException("Discount", discount.id)
        }

        if (!operatorsRepository.exists(discount.operatorId)) {
            throw ModelNotFoundException("Operator", discount.operatorId)
        }

        discountsRepository.create(discount)
            ?: throw ModelCreateException("Discount", discount.id)
    }
}
