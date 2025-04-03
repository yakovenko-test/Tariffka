package code.yakovenko.tariffka.domain.usecase.update

import code.yakovenko.tariffka.domain.model.Tariff
import code.yakovenko.tariffka.domain.repository.TariffRepository
import javax.inject.Inject

class UpdateTariffUseCase @Inject constructor(
    private val tariffRepository: TariffRepository
) {
    suspend operator fun invoke(tariff: Tariff) {
        tariffRepository.update(tariff)
    }
}
