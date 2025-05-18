package test.yakovenko.domain.usecase.feedback

import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.repository.FeedbacksRepository
import test.yakovenko.domain.repository.OperatorsRepository
import java.util.UUID

class FindOperatorFeedbacksUseCase(
    private val feedbacksRepository: FeedbacksRepository,
    private val operatorsRepository: OperatorsRepository,
) {
    suspend operator fun invoke(operatorId: UUID) = runCatching {
        if (!operatorsRepository.exists(operatorId)) {
            throw ModelNotFoundException("Operator", operatorId)
        }

        feedbacksRepository.findForOperator(operatorId)
    }
}