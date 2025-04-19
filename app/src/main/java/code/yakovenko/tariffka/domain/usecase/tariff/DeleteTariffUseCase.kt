package code.yakovenko.tariffka.domain.usecase.tariff

import code.yakovenko.tariffka.domain.exception.ModelNotFoundException
import code.yakovenko.tariffka.domain.repository.TariffRepository
import java.util.UUID
import javax.inject.Inject

class DeleteTariffUseCase @Inject constructor(
    private val tariffRepository: TariffRepository
) {
    operator fun invoke(tariffId: UUID) {
        if (tariffRepository.notContainsId(tariffId)) {
            throw ModelNotFoundException("Tariff", tariffId)
        }

        tariffRepository.deleteById(tariffId)
    }
}