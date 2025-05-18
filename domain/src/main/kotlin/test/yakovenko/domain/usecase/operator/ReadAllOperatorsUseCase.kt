package test.yakovenko.domain.usecase.operator

import test.yakovenko.domain.repository.OperatorsRepository
import javax.inject.Inject

class ReadAllOperatorsUseCase @Inject constructor(
    private val operatorsRepository: OperatorsRepository,
) {
    suspend operator fun invoke() = runCatching { operatorsRepository.readAll() }
}