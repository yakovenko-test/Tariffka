package code.yakovenko.tariffka.domain.usecase.read

import code.yakovenko.tariffka.domain.model.TariffDiscount
import code.yakovenko.tariffka.domain.repository.TariffDiscountRepository
import kotlinx.coroutines.flow.Flow

class ReadTariffDiscountByIdUseCase(
    private val tariffDiscountRepository: TariffDiscountRepository
) {
    operator fun invoke(tariffDiscountId: Int): Flow<TariffDiscount?> {
        return tariffDiscountRepository.readById(tariffDiscountId)
    }
}