package usecase.tariff

import exception.ModelNotFoundException
import repository.OperatorsRepository
import repository.TariffsRepository
import java.util.*
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