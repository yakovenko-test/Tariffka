package code.yakovenko.tariffka.domain.usecase.update

import code.yakovenko.tariffka.domain.model.Tariff
import code.yakovenko.tariffka.domain.repository.TariffRepository

class UpdateTariffUseCase(
    private val tariffRepository: TariffRepository
) {
    suspend operator fun invoke(tariff: Tariff) {
        tariffRepository.update(tariff)
    }
}
