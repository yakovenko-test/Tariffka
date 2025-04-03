package code.yakovenko.tariffka.domain.usecase.read

import code.yakovenko.tariffka.domain.repository.TariffDiscountRepository
import javax.inject.Inject

class ReadTariffDiscountsUseCase @Inject constructor(
    private val tariffDiscountRepository: TariffDiscountRepository,
) {
    suspend operator fun invoke() = tariffDiscountRepository.readAll()
}
