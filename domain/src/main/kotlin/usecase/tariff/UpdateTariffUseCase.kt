package usecase.tariff

import exception.ModelNotFoundException
import exception.ModelUpdateException
import model.Tariff
import repository.OperatorsRepository
import repository.TariffsRepository
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