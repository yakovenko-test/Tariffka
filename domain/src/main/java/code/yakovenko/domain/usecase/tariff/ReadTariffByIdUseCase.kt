package code.yakovenko.domain.usecase.tariff

import code.yakovenko.domain.exception.ModelNotFoundException
import code.yakovenko.domain.model.Tariff
import code.yakovenko.domain.repository.TariffsRepository
import java.util.UUID
import javax.inject.Inject

class ReadTariffByIdUseCase @Inject constructor(
    private val tariffsRepository: TariffsRepository
) {
    operator fun invoke(tariffId: UUID): Tariff {
        return tariffsRepository.readById(tariffId)
            ?: throw ModelNotFoundException("Tariff", tariffId)
    }
}