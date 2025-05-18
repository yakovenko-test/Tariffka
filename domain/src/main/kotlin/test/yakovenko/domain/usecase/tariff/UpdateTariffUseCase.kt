package test.yakovenko.domain.usecase.tariff

import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.exception.ModelUpdateException
import test.yakovenko.domain.model.Tariff
import test.yakovenko.domain.repository.OperatorsRepository
import test.yakovenko.domain.repository.TariffsRepository
import javax.inject.Inject

class UpdateTariffUseCase @Inject constructor(
    private val tariffsRepository: TariffsRepository,
    private val operatorsRepository: OperatorsRepository,
) {
    suspend operator fun invoke(tariff: Tariff) = runCatching {
        if (!tariffsRepository.exists(tariff.id)) {
            throw ModelNotFoundException("Tariff", tariff.id)
        }

        if (!operatorsRepository.exists(tariff.operatorId)) {
            throw ModelNotFoundException("Operator", tariff.operatorId)
        }

        tariffsRepository.update(tariff)
            ?: throw ModelUpdateException("Tariff", tariff.id)
    }
}