package repository

import model.Feedback
import model.Feedback.*
import java.util.*

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
