package code.yakovenko.tariffka.domain.usecase.create

import code.yakovenko.tariffka.domain.model.TariffDiscount
import code.yakovenko.tariffka.domain.repository.TariffDiscountRepository

class CreateTariffDiscountUseCase(
    private val tariffDiscountRepository: TariffDiscountRepository
) {
    suspend operator fun invoke(tariffDiscount: TariffDiscount) {
        tariffDiscountRepository.create(tariffDiscount)
    }
}
