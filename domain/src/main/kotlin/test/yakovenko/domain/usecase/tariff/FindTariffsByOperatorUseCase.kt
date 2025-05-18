package test.yakovenko.domain.usecase.tariff

import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.repository.OperatorsRepository
import test.yakovenko.domain.repository.TariffsRepository
import java.util.UUID
import javax.inject.Inject

class FindTariffsByOperatorUseCase @Inject constructor(
    private val tariffsRepository: TariffsRepository,
    private val operatorsRepository: OperatorsRepository,
) {
    suspend operator fun invoke(operatorId: UUID) = runCatching {
        if (!operatorsRepository.exists(operatorId)) {
            throw ModelNotFoundException("Operator", operatorId)
        }

        tariffsRepository.findByOperator(operatorId)
    }
}