package code.yakovenko.tariffka.domain.usecase.create

import code.yakovenko.tariffka.domain.model.Tariff
import code.yakovenko.tariffka.domain.repository.TariffRepository

class CreateTariffUseCase(
    private val tariffRepository: TariffRepository
) {
    suspend operator fun invoke(tariff: Tariff) {
        tariffRepository.create(tariff)
    }
}
