package code.yakovenko.domain.usecase.discount

import code.yakovenko.domain.exception.ModelNotFoundException
import code.yakovenko.domain.repository.DiscountsRepository
import java.util.UUID
import javax.inject.Inject

class DeleteDiscountUseCase @Inject constructor(
    private val discountsRepository: DiscountsRepository
) {
    operator fun invoke(discountId: UUID) {
        if (discountsRepository.notContainsId(discountId)) {
            throw ModelNotFoundException("Discount", discountId)
        }

        discountsRepository.deleteById(discountId)
    }
}