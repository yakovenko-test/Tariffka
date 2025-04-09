package code.yakovenko.tariffka.domain.usecase.read

import code.yakovenko.tariffka.domain.model.TariffDiscount
import code.yakovenko.tariffka.domain.repository.TariffDiscountRepository
import kotlinx.coroutines.flow.Flow

class ReadAllTariffDiscountsByOperatorIdUseCase(
    private val tariffDiscountRepository: TariffDiscountRepository
) {
    operator fun invoke(operatorId: Int): Flow<List<TariffDiscount>> {
        return tariffDiscountRepository.readByOperatorId(operatorId)
    }
}