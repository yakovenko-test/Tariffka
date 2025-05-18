package test.yakovenko.domain.usecase.feedback.create

import test.yakovenko.domain.exception.ModelCreateException
import test.yakovenko.domain.exception.ModelDuplicateException
import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.model.Feedback.OperatorFeedback
import test.yakovenko.domain.repository.FeedbacksRepository
import test.yakovenko.domain.repository.OperatorsRepository
import test.yakovenko.domain.repository.UsersRepository
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