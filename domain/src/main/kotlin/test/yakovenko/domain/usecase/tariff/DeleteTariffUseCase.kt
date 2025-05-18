package test.yakovenko.domain.usecase.tariff

import test.yakovenko.domain.exception.ModelDeleteException
import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.repository.TariffsRepository
import java.util.UUID
import javax.inject.Inject

class DeleteTariffUseCase @Inject constructor(
    private val tariffsRepository: TariffsRepository,
) {
    suspend operator fun invoke(tariffId: UUID) = runCatching {
        if (!tariffsRepository.exists(tariffId)) {
            throw ModelNotFoundException("Tariff", tariffId)
        }

        if (!tariffsRepository.delete(tariffId)) {
            throw ModelDeleteException("Tariff", tariffId)
        }
    }
}