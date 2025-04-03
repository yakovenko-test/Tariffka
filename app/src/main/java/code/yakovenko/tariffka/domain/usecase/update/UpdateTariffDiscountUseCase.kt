package code.yakovenko.tariffka.domain.usecase.update

import code.yakovenko.tariffka.domain.model.TariffDiscount
import code.yakovenko.tariffka.domain.repository.TariffDiscountRepository
import javax.inject.Inject

class UpdateTariffDiscountUseCase @Inject constructor(
    private val tariffDiscountRepository: TariffDiscountRepository
) {
    suspend operator fun invoke(tariffDiscount: TariffDiscount) {
        tariffDiscountRepository.update(tariffDiscount)
    }
}
