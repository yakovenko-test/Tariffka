package code.yakovenko.tariffka.domain.usecase.read

import code.yakovenko.tariffka.domain.model.Tariff
import code.yakovenko.tariffka.domain.repository.TariffRepository
import kotlinx.coroutines.flow.Flow

class ReadTariffByIdUseCase(
    private val tariffRepository: TariffRepository
) {
    operator fun invoke(tariffId: Int): Flow<Tariff?> {
        return tariffRepository.readById(tariffId)
    }
}