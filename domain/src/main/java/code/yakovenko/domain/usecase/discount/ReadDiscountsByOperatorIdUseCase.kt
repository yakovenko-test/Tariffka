package code.yakovenko.domain.usecase.discount

import code.yakovenko.domain.exception.ModelNotFoundException
import code.yakovenko.domain.model.Discount
import code.yakovenko.domain.repository.DiscountsRepository
import code.yakovenko.domain.repository.OperatorsRepository
import java.util.UUID
import javax.inject.Inject

class ReadDiscountsByOperatorIdUseCase @Inject constructor(
    private val discountsRepository: DiscountsRepository,
    private val operatorsRepository: OperatorsRepository
) {
    operator fun invoke(operatorId: UUID): Collection<Discount> {
        if (operatorsRepository.notContainsId(operatorId)) {
            throw ModelNotFoundException("Operator", operatorId)
        }

        return discountsRepository.readByOperatorId(operatorId)
    }
}