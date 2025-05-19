package usecase.feedback

import exception.ModelNotFoundException
import repository.FeedbacksRepository
import repository.OperatorsRepository
import java.util.*

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