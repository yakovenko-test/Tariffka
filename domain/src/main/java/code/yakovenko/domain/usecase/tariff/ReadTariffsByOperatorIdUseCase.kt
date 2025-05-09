package code.yakovenko.domain.usecase.tariff

import code.yakovenko.domain.exception.ModelNotFoundException
import code.yakovenko.domain.model.Tariff
import code.yakovenko.domain.repository.OperatorsRepository
import code.yakovenko.domain.repository.TariffsRepository
import java.util.UUID
import javax.inject.Inject

class ReadTariffsByOperatorIdUseCase @Inject constructor(
    private val tariffsRepository: TariffsRepository,
    private val operatorsRepository: OperatorsRepository
) {
    operator fun invoke(operatorId: UUID): Collection<Tariff> {
        if (operatorsRepository.notContainsId(operatorId)) {
            throw ModelNotFoundException("Operator", operatorId)
        }

        return tariffsRepository.readByOperatorId(operatorId)
    }
}