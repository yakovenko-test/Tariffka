package code.yakovenko.tariffka.domain.usecase.read

import code.yakovenko.tariffka.domain.repository.OperatorRepository
import javax.inject.Inject

class ReadOperatorsUseCase @Inject constructor(
    private val operatorRepository: OperatorRepository,
) {
    suspend operator fun invoke() = operatorRepository.readAll()
}
