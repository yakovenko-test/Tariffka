package test.yakovenko.domain.usecase.discount

import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.exception.ModelReadException
import test.yakovenko.domain.repository.DiscountsRepository
import java.util.UUID
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
