package code.yakovenko.tariffka.domain.usecase.delete

import code.yakovenko.tariffka.domain.repository.TariffDiscountRepository
import javax.inject.Inject

class DeleteTariffDiscountUseCase @Inject constructor(
    private val tariffDiscountRepository: TariffDiscountRepository
) {
    suspend operator fun invoke(tariffDiscountId: IdType) {
        tariffDiscountRepository.deleteById(tariffDiscountId)
    }
}
