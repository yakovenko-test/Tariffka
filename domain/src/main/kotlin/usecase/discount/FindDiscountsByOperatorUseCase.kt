package usecase.discount

import exception.ModelNotFoundException
import repository.DiscountsRepository
import repository.OperatorsRepository
import java.util.*
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