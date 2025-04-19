package code.yakovenko.tariffka.domain.usecase.tariff

import code.yakovenko.tariffka.domain.exception.ModelNotFoundException
import code.yakovenko.tariffka.domain.model.Tariff
import code.yakovenko.tariffka.domain.repository.TariffRepository
import java.util.UUID
import javax.inject.Inject

class ReadTariffByIdUseCase @Inject constructor(
    private val tariffRepository: TariffRepository
) {
    operator fun invoke(tariffId: UUID): Tariff {
        return tariffRepository.readById(tariffId)
            ?: throw ModelNotFoundException("Tariff", tariffId)
    }
}