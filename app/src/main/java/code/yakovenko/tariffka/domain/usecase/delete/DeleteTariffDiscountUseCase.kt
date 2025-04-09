package code.yakovenko.tariffka.domain.usecase.delete

import code.yakovenko.tariffka.domain.repository.TariffDiscountRepository

class DeleteTariffDiscountUseCase(
    private val tariffDiscountRepository: TariffDiscountRepository
) {
    suspend operator fun invoke(tariffDiscountId: Int) {
        tariffDiscountRepository.deleteById(tariffDiscountId)
    }
}
