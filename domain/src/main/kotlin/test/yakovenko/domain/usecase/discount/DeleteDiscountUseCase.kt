package test.yakovenko.domain.usecase.discount

import test.yakovenko.domain.exception.ModelDeleteException
import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.repository.DiscountsRepository
import java.util.UUID
import javax.inject.Inject

class DeleteDiscountUseCase @Inject constructor(
    private val discountsRepository: DiscountsRepository,
) {
    suspend operator fun invoke(discountId: UUID) = runCatching {
        if (!discountsRepository.exists(discountId)) {
            throw ModelNotFoundException("Discount", discountId)
        }

        if (!discountsRepository.delete(discountId)) {
            throw ModelDeleteException("Discount", discountId)
        }
    }
}