package test.yakovenko.domain.repository

import test.yakovenko.domain.model.Feedback
import test.yakovenko.domain.model.Feedback.OperatorFeedback
import test.yakovenko.domain.model.Feedback.ServiceFeedback
import test.yakovenko.domain.model.Feedback.TariffFeedback
import java.util.UUID

interface FeedbacksRepository {
    suspend fun create(feedback: Feedback): Feedback?
    suspend fun read(feedbackId: UUID): Feedback?
    suspend fun update(feedback: Feedback): Feedback?
    suspend fun delete(feedbackId: UUID): Boolean

    suspend fun readAll(): Collection<Feedback>

    suspend fun findByAuthor(userId: UUID): Collection<Feedback>

    suspend fun findForOperator(operatorId: UUID): Collection<OperatorFeedback>
    suspend fun findForService(serviceId: UUID): Collection<ServiceFeedback>
    suspend fun findForTariff(tariffId: UUID): Collection<TariffFeedback>

    suspend fun exists(feedbackId: UUID): Boolean
}
