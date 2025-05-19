package usecase.tariff

import exception.ModelDeleteException
import exception.ModelNotFoundException
import repository.TariffsRepository
import java.util.*
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