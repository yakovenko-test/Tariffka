package test.yakovenko.domain.usecase.discount

import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.repository.DiscountsRepository
import test.yakovenko.domain.repository.OperatorsRepository
import java.util.UUID
import javax.inject.Inject

class FindDiscountsByOperatorUseCase @Inject constructor(
    private val discountsRepository: DiscountsRepository,
    private val operatorsRepository: OperatorsRepository,
) {
    suspend operator fun invoke(operatorId: UUID) = runCatching {
        if (!operatorsRepository.exists(operatorId)) {
            throw ModelNotFoundException("Operator", operatorId)
        }

        discountsRepository.findByOperator(operatorId)
    }
}