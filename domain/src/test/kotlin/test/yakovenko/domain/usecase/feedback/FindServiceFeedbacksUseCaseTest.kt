package test.yakovenko.domain.usecase.feedback

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.model.Feedback.ServiceFeedback
import test.yakovenko.domain.model.TestFeedback
import test.yakovenko.domain.repository.FeedbacksRepository
import test.yakovenko.domain.repository.ServicesRepository
import java.util.UUID

class FindServiceFeedbacksUseCaseTest {
    private lateinit var findServiceFeedbacksUseCase: FindServiceFeedbacksUseCase

    private val feedbacksRepository: FeedbacksRepository = mockk()
    private val servicesRepository: ServicesRepository = mockk()

    private val serviceId = UUID.randomUUID()

    @BeforeEach
    fun setUp() {
        findServiceFeedbacksUseCase = FindServiceFeedbacksUseCase(
            feedbacksRepository, servicesRepository
        )
    }

    @Test
    fun `should find feedbacks for existing service`() = runTest {
        // Arrange
        val feedbacks = listOf(
            TestFeedback.createServiceFeedback(authorId = UUID.randomUUID(), serviceId = serviceId),
            TestFeedback.createServiceFeedback(authorId = UUID.randomUUID(), serviceId = serviceId)
        )

        coEvery { servicesRepository.exists(serviceId) } returns true
        coEvery { feedbacksRepository.findForService(serviceId) } returns feedbacks

        // Act
        val result = findServiceFeedbacksUseCase(serviceId)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(feedbacks, result.getOrNull())

        coVerifySequence {
            servicesRepository.exists(serviceId)
            feedbacksRepository.findForService(serviceId)
        }
    }

    @Test
    fun `should return empty collection when no service feedbacks exist`() = runTest {
        // Arrange
        coEvery { servicesRepository.exists(serviceId) } returns true
        coEvery { feedbacksRepository.findForService(serviceId) } returns emptyList<ServiceFeedback>()

        // Act
        val result = findServiceFeedbacksUseCase(serviceId)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(emptyList<ServiceFeedback>(), result.getOrNull())

        coVerifySequence {
            servicesRepository.exists(serviceId)
            feedbacksRepository.findForService(serviceId)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when service does not exist`() = runTest {
        // Arrange
        coEvery { servicesRepository.exists(serviceId) } returns false

        // Act
        val result = findServiceFeedbacksUseCase(serviceId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerify(exactly = 1) { servicesRepository.exists(serviceId) }
        coVerify(exactly = 0) { feedbacksRepository.findForService(any()) }
    }
}
