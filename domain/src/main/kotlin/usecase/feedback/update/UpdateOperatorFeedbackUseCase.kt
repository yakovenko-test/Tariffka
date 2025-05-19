package usecase.feedback.update

import exception.ModelNotFoundException
import exception.ModelUpdateException
import model.Feedback.OperatorFeedback
import repository.FeedbacksRepository
import repository.OperatorsRepository
import repository.UsersRepository
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