package usecase.discount

import exception.ModelNotFoundException
import exception.ModelReadException
import repository.DiscountsRepository
import java.util.*
import javax.inject.Inject

class ReadDiscountUseCase @Inject constructor(
    private val discountsRepository: DiscountsRepository,
) {
    suspend operator fun invoke(discountId: UUID) = runCatching {
        if (!discountsRepository.exists(discountId)) {
            throw ModelNotFoundException("Discount", discountId)
        }

        discountsRepository.read(discountId)
            ?: throw ModelReadException("Discount", discountId)
    }
}
