package test.yakovenko.domain.usecase.tariff

import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.exception.ModelReadException
import test.yakovenko.domain.repository.TariffsRepository
import java.util.UUID
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