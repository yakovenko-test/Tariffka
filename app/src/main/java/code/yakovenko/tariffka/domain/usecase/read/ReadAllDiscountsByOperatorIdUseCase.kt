package code.yakovenko.tariffka.domain.usecase.read

import code.yakovenko.tariffka.domain.model.Discount
import code.yakovenko.tariffka.domain.repository.DiscountRepository
import kotlinx.coroutines.flow.Flow

class ReadAllDiscountsByOperatorIdUseCase(
    private val discountRepository: DiscountRepository
) {
    operator fun invoke(operatorId: Int): Flow<List<Discount>> {
        return discountRepository.readByOperatorId(operatorId)
    }
}
