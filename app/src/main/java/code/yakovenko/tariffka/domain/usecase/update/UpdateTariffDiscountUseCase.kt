package code.yakovenko.tariffka.domain.usecase.update

import code.yakovenko.tariffka.domain.model.TariffDiscount
import code.yakovenko.tariffka.domain.repository.TariffDiscountRepository

class UpdateTariffDiscountUseCase(
    private val tariffDiscountRepository: TariffDiscountRepository
) {
    suspend operator fun invoke(tariffDiscount: TariffDiscount) {
        tariffDiscountRepository.update(tariffDiscount)
    }
}
