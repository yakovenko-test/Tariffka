package code.yakovenko.tariffka.domain.usecase.discount

import code.yakovenko.tariffka.domain.exception.ModelNotFoundException
import code.yakovenko.tariffka.domain.repository.DiscountRepository
import java.util.UUID
import javax.inject.Inject

class DeleteDiscountUseCase @Inject constructor(
    private val discountRepository: DiscountRepository
) {
    operator fun invoke(discountId: UUID) {
        if (discountRepository.notContainsId(discountId)) {
            throw ModelNotFoundException("Discount", discountId)
        }

        discountRepository.deleteById(discountId)
    }
}