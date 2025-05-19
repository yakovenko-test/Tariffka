package usecase.tariff

import exception.ModelCreateException
import exception.ModelDuplicateException
import exception.ModelNotFoundException
import model.Tariff
import repository.OperatorsRepository
import repository.TariffsRepository
import javax.inject.Inject

class CreateTariffUseCase @Inject constructor(
    private val tariffsRepository: TariffsRepository,
    private val operatorsRepository: OperatorsRepository,
) {
    suspend operator fun invoke(tariff: Tariff) = runCatching {
        if (tariffsRepository.exists(tariff.id)) {
            throw ModelDuplicateException("Tariff", tariff.id)
        }

        if (!operatorsRepository.exists(tariff.operatorId)) {
            throw ModelNotFoundException("Operator", tariff.operatorId)
        }

        tariffsRepository.create(tariff)
            ?: throw ModelCreateException("Tariff", tariff.id)
    }
}