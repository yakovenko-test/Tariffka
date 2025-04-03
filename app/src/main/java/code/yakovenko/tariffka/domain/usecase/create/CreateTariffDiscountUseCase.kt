package code.yakovenko.tariffka.domain.usecase.create

import code.yakovenko.tariffka.domain.model.TariffDiscount
import code.yakovenko.tariffka.domain.repository.TariffDiscountRepository
import javax.inject.Inject

class CreateTariffDiscountUseCase @Inject constructor(
    private val tariffDiscountRepository: TariffDiscountRepository
) {
    suspend operator fun invoke(tariffDiscount: TariffDiscount) {
        tariffDiscountRepository.create(tariffDiscount)
    }
}
