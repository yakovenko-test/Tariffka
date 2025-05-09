package code.yakovenko.domain.usecase.tariff

import code.yakovenko.domain.exception.ModelNotFoundException
import code.yakovenko.domain.repository.TariffsRepository
import java.util.UUID
import javax.inject.Inject

class DeleteTariffUseCase @Inject constructor(
    private val tariffsRepository: TariffsRepository
) {
    operator fun invoke(tariffId: UUID) {
        if (tariffsRepository.notContainsId(tariffId)) {
            throw ModelNotFoundException("Tariff", tariffId)
        }

        tariffsRepository.deleteById(tariffId)
    }
}