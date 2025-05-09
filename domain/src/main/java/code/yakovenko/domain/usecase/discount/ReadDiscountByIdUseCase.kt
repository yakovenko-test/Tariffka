package code.yakovenko.domain.usecase.discount

import code.yakovenko.domain.exception.ModelNotFoundException
import code.yakovenko.domain.model.Discount
import code.yakovenko.domain.repository.DiscountsRepository
import java.util.UUID
import javax.inject.Inject

class ReadDiscountByIdUseCase @Inject constructor(
    private val discountsRepository: DiscountsRepository
) {
    operator fun invoke(discountId: UUID): Discount {
        return discountsRepository.readById(discountId)
            ?: throw ModelNotFoundException("Discount", discountId)
    }
}