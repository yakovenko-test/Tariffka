package code.yakovenko.tariffka.domain.usecase.read

import code.yakovenko.tariffka.domain.model.Discount
import code.yakovenko.tariffka.domain.repository.DiscountRepository
import kotlinx.coroutines.flow.Flow

class ReadDiscountByIdUseCase(
    private val discountRepository: DiscountRepository
) {
    operator fun invoke(discountId: Int): Flow<Discount?> {
        return discountRepository.readById(discountId)
    }
}