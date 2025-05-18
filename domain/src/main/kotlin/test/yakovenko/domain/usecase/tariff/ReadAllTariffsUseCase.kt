package test.yakovenko.domain.usecase.tariff

import test.yakovenko.domain.repository.TariffsRepository
import javax.inject.Inject

class ReadAllTariffsUseCase @Inject constructor(
    private val tariffsRepository: TariffsRepository,
) {
    suspend operator fun invoke() = runCatching { tariffsRepository.readAll() }
}