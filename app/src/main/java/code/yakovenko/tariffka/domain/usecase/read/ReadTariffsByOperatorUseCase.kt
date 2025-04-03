package code.yakovenko.tariffka.domain.usecase.read

import code.yakovenko.tariffka.domain.model.Tariff
import code.yakovenko.tariffka.domain.repository.TariffRepository
import javax.inject.Inject

class ReadTariffsByOperatorUseCase @Inject constructor(
    private val tariffRepository: TariffRepository,
) {
    suspend operator fun invoke(operatorId: IdType): List<Tariff> {
        return tariffRepository.readAll().filter {
            it.operatorId == operatorId
        }
    }
}
