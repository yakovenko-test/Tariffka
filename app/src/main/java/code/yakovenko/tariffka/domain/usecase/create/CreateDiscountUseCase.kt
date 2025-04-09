package code.yakovenko.tariffka.domain.usecase.create

import code.yakovenko.tariffka.domain.model.Discount
import code.yakovenko.tariffka.domain.repository.DiscountRepository

class CreateDiscountUseCase(
    private val discountRepository: DiscountRepository
) {
    suspend operator fun invoke(discount: Discount) {
        discountRepository.create(discount)
    }
}
