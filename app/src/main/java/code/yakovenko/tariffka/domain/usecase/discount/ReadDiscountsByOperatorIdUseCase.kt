package code.yakovenko.tariffka.domain.usecase.discount

import code.yakovenko.tariffka.domain.exception.ModelNotFoundException
import code.yakovenko.tariffka.domain.model.Discount
import code.yakovenko.tariffka.domain.repository.DiscountRepository
import code.yakovenko.tariffka.domain.repository.OperatorRepository
import java.util.UUID
import javax.inject.Inject

class ReadDiscountsByOperatorIdUseCase @Inject constructor(
    private val discountRepository: DiscountRepository,
    private val operatorRepository: OperatorRepository
) {
    operator fun invoke(operatorId: UUID): Collection<Discount> {
        if (operatorRepository.notContainsId(operatorId)) {
            throw ModelNotFoundException("Operator", operatorId)
        }

        return discountRepository.readByOperatorId(operatorId)
    }
}