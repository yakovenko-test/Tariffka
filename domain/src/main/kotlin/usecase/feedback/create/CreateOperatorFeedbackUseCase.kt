package usecase.feedback.create

import exception.ModelCreateException
import exception.ModelDuplicateException
import exception.ModelNotFoundException
import model.Feedback.OperatorFeedback
import repository.FeedbacksRepository
import repository.OperatorsRepository
import repository.UsersRepository
import javax.inject.Inject

class CreateOperatorFeedbackUseCase @Inject constructor(
    private val feedbacksRepository: FeedbacksRepository,
    private val usersRepository: UsersRepository,
    private val operatorsRepository: OperatorsRepository,
) {
    suspend operator fun invoke(feedback: OperatorFeedback) = runCatching {
        if (feedbacksRepository.exists(feedback.id)) {
            throw ModelDuplicateException("Feedback", feedback.id)
        }

        if (!usersRepository.exists(feedback.authorId)) {
            throw ModelNotFoundException("User", feedback.authorId)
        }

        if (!operatorsRepository.exists(feedback.operatorId)) {
            throw ModelNotFoundException("Operator", feedback.operatorId)
        }

        feedbacksRepository.create(feedback)
            ?: throw ModelCreateException("Feedback", feedback.id)
    }
}