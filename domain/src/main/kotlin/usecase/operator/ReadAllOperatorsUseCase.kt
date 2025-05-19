package usecase.operator

import repository.OperatorsRepository
import javax.inject.Inject

class ReadAllOperatorsUseCase @Inject constructor(
    private val operatorsRepository: OperatorsRepository,
) {
    suspend operator fun invoke() = runCatching { operatorsRepository.readAll() }
}