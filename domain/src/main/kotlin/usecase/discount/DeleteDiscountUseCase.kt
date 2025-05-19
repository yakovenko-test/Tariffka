package usecase.discount

import exception.ModelDeleteException
import exception.ModelNotFoundException
import repository.DiscountsRepository
import java.util.*
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