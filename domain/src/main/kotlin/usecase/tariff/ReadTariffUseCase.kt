package usecase.tariff

import exception.ModelNotFoundException
import exception.ModelReadException
import repository.TariffsRepository
import java.util.*
import javax.inject.Inject

class ReadTariffUseCase @Inject constructor(
    private val tariffsRepository: TariffsRepository,
) {
    suspend operator fun invoke(tariffId: UUID) = runCatching {
        if (!tariffsRepository.exists(tariffId)) {
            throw ModelNotFoundException("Tariff", tariffId)
        }

        tariffsRepository.read(tariffId)
            ?: throw ModelReadException("Tariff", tariffId)
    }
}