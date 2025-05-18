package test.yakovenko.domain.usecase.feedback.update

import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.exception.ModelUpdateException
import test.yakovenko.domain.model.Feedback.OperatorFeedback
import test.yakovenko.domain.repository.FeedbacksRepository
import test.yakovenko.domain.repository.OperatorsRepository
import test.yakovenko.domain.repository.UsersRepository
import javax.inject.Inject

class UpdateOperatorFeedbackUseCase @Inject constructor(
    private val feedbacksRepository: FeedbacksRepository,
    private val usersRepository: UsersRepository,
    private val operatorsRepository: OperatorsRepository,
) {
    suspend operator fun invoke(feedback: OperatorFeedback) = runCatching {
        if (!feedbacksRepository.exists(feedback.id)) {
            throw ModelNotFoundException("Feedback", feedback.id)
        }

        if (!usersRepository.exists(feedback.authorId)) {
            throw ModelNotFoundException("User", feedback.authorId)
        }

        if (!operatorsRepository.exists(feedback.operatorId)) {
            throw ModelNotFoundException("Operator", feedback.operatorId)
        }

        feedbacksRepository.update(feedback)
            ?: throw ModelUpdateException("Feedback", feedback.id)
    }
}