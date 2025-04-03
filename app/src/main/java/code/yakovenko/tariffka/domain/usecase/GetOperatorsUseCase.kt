package code.yakovenko.tariffka.domain.usecase

import code.yakovenko.tariffka.domain.repository.OperatorRepository
import javax.inject.Inject

class GetOperatorsUseCase @Inject constructor(
    private val repository: OperatorRepository,
) {
    suspend operator fun invoke() = repository.getAll()
}
