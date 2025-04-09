package code.yakovenko.tariffka.domain.usecase.update

import code.yakovenko.tariffka.domain.model.Discount
import code.yakovenko.tariffka.domain.repository.DiscountRepository

class UpdateDiscountUseCase(
    private val discountRepository: DiscountRepository
) {
    suspend operator fun invoke(discount: Discount) {
        discountRepository.update(discount)
    }
}
