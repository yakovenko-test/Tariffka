package code.yakovenko.tariffka.domain.usecase.update

import code.yakovenko.tariffka.domain.exception.OperatorNotFoundException
import code.yakovenko.tariffka.domain.model.Discount
import code.yakovenko.tariffka.domain.repository.DiscountRepository
import code.yakovenko.tariffka.domain.repository.OperatorRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class UpdateDiscountUseCase @Inject constructor(
    private val discountRepository: DiscountRepository,
    private val operatorRepository: OperatorRepository
) {
    suspend operator fun invoke(discount: Discount) {
        if (operatorRepository.readById(discount.operatorId).firstOrNull() == null) {
            throw OperatorNotFoundException(discount.operatorId)
        }

        discountRepository.update(discount)
    }
}
