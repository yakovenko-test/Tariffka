package code.yakovenko.tariffka.domain.usecase.tariff

import code.yakovenko.tariffka.domain.exception.ModelDuplicateException
import code.yakovenko.tariffka.domain.exception.ModelNotFoundException
import code.yakovenko.tariffka.domain.model.Tariff
import code.yakovenko.tariffka.domain.repository.OperatorRepository
import code.yakovenko.tariffka.domain.repository.TariffRepository
import javax.inject.Inject

class CreateTariffUseCase @Inject constructor(
    private val tariffRepository: TariffRepository,
    private val operatorRepository: OperatorRepository
) {
    operator fun invoke(tariff: Tariff): Tariff {
        if (operatorRepository.notContainsId(tariff.operatorId)) {
            throw ModelNotFoundException("Operator", tariff.operatorId)
        }

        if (tariffRepository.containsId(tariff.id)) {
            throw ModelDuplicateException("Tariff", tariff.id)
        }

        return tariffRepository.create(tariff)
    }
}