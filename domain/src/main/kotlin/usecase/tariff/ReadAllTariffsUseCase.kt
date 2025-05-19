package usecase.tariff

import repository.TariffsRepository
import javax.inject.Inject

class ReadAllTariffsUseCase @Inject constructor(
    private val tariffsRepository: TariffsRepository,
) {
    suspend operator fun invoke() = runCatching { tariffsRepository.readAll() }
}