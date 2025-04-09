package code.yakovenko.tariffka.domain.usecase.delete

import code.yakovenko.tariffka.domain.repository.DiscountRepository

class DeleteDiscountUseCase(
    private val discountRepository: DiscountRepository
) {
    suspend operator fun invoke(discountId: Int) {
        discountRepository.deleteById(discountId)
    }
}
