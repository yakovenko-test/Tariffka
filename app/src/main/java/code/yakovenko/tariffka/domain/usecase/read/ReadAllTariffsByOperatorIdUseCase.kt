package code.yakovenko.tariffka.domain.usecase.read

import code.yakovenko.tariffka.domain.model.Tariff
import code.yakovenko.tariffka.domain.repository.TariffRepository
import kotlinx.coroutines.flow.Flow

class ReadAllTariffsByOperatorIdUseCase(
    private val tariffRepository: TariffRepository
) {
    operator fun invoke(operatorId: Int): Flow<List<Tariff>> {
        return tariffRepository.readByOperatorId(operatorId)
    }
}