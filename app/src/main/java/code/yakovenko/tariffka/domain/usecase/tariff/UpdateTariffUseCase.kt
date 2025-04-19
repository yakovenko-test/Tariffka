package code.yakovenko.tariffka.domain.usecase.tariff

import code.yakovenko.tariffka.domain.exception.ModelNotFoundException
import code.yakovenko.tariffka.domain.model.Tariff
import code.yakovenko.tariffka.domain.repository.OperatorRepository
import code.yakovenko.tariffka.domain.repository.TariffRepository
import javax.inject.Inject

class UpdateTariffUseCase @Inject constructor(
    private val tariffRepository: TariffRepository,
    private val operatorRepository: OperatorRepository
) {
    operator fun invoke(tariff: Tariff): Tariff? {
        if (operatorRepository.notContainsId(tariff.operatorId)) {
            throw ModelNotFoundException("Operator", tariff.operatorId)
        }

        if (tariffRepository.notContainsId(tariff.id)) {
            throw ModelNotFoundException("Tariff", tariff.id)
        }

        return tariffRepository.update(tariff)
    }
}