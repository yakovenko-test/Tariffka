package code.yakovenko.tariffka.domain.usecase.tariff

import code.yakovenko.tariffka.domain.exception.ModelNotFoundException
import code.yakovenko.tariffka.domain.model.Tariff
import code.yakovenko.tariffka.domain.repository.OperatorRepository
import code.yakovenko.tariffka.domain.repository.TariffRepository
import java.util.UUID
import javax.inject.Inject

class ReadTariffsByOperatorIdUseCase @Inject constructor(
    private val tariffRepository: TariffRepository,
    private val operatorRepository: OperatorRepository
) {
    operator fun invoke(operatorId: UUID): Collection<Tariff> {
        if (operatorRepository.notContainsId(operatorId)) {
            throw ModelNotFoundException("Operator", operatorId)
        }

        return tariffRepository.readByOperatorId(operatorId)
    }
}