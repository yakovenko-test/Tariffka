package test.yakovenko.domain.usecase.tariff

import test.yakovenko.domain.exception.ModelCreateException
import test.yakovenko.domain.exception.ModelDuplicateException
import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.model.Tariff
import test.yakovenko.domain.repository.OperatorsRepository
import test.yakovenko.domain.repository.TariffsRepository
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