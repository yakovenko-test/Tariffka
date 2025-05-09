package code.yakovenko.domain.usecase.tariff

import code.yakovenko.domain.exception.ModelNotFoundException
import code.yakovenko.domain.model.Tariff
import code.yakovenko.domain.repository.OperatorsRepository
import code.yakovenko.domain.repository.TariffsRepository
import javax.inject.Inject

class UpdateTariffUseCase @Inject constructor(
    private val tariffsRepository: TariffsRepository,
    private val operatorsRepository: OperatorsRepository
) {
    operator fun invoke(tariff: Tariff): Tariff? {
        if (operatorsRepository.notContainsId(tariff.operatorId)) {
            throw ModelNotFoundException("Operator", tariff.operatorId)
        }

        if (tariffsRepository.notContainsId(tariff.id)) {
            throw ModelNotFoundException("Tariff", tariff.id)
        }

        return tariffsRepository.update(tariff)
    }
}