package test.yakovenko.domain.usecase.discount

import test.yakovenko.domain.exception.ModelCreateException
import test.yakovenko.domain.exception.ModelDuplicateException
import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.model.Discount
import test.yakovenko.domain.repository.DiscountsRepository
import test.yakovenko.domain.repository.OperatorsRepository
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
