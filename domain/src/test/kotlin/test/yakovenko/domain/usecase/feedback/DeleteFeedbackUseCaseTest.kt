package test.yakovenko.domain.usecase.feedback

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import test.yakovenko.domain.exception.ModelDeleteException
import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.repository.FeedbacksRepository
import java.util.UUID

class DeleteFeedbackUseCaseTest {
    private lateinit var deleteFeedbackUseCase: DeleteFeedbackUseCase

    private val feedbacksRepository: FeedbacksRepository = mockk()

    private val feedbackId = UUID.randomUUID()

    @BeforeEach
    fun setUp() {
        deleteFeedbackUseCase = DeleteFeedbackUseCase(feedbacksRepository)
    }

    @Test
    fun `should delete feedback successfully`() = runTest {
        // Arrange
        coEvery { feedbacksRepository.exists(feedbackId) } returns true
        coEvery { feedbacksRepository.delete(feedbackId) } returns true

        // Act
        val result = deleteFeedbackUseCase(feedbackId)

        // Assert
        assertTrue(result.isSuccess)

        coVerifySequence {
            feedbacksRepository.exists(feedbackId)
            feedbacksRepository.delete(feedbackId)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when feedback does not exist`() = runTest {
        // Arrange
        coEvery { feedbacksRepository.exists(feedbackId) } returns false

        // Act
        val result = deleteFeedbackUseCase(feedbackId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerify(exactly = 1) { feedbacksRepository.exists(feedbackId) }
        coVerify(exactly = 0) { feedbacksRepository.delete(any()) }
    }

    @Test
    fun `should fail with ModelDeleteException when delete returns false`() = runTest {
        // Arrange
        coEvery { feedbacksRepository.exists(feedbackId) } returns true
        coEvery { feedbacksRepository.delete(feedbackId) } returns false

        // Act
        val result = deleteFeedbackUseCase(feedbackId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelDeleteException)

        coVerifySequence {
            feedbacksRepository.exists(feedbackId)
            feedbacksRepository.delete(feedbackId)
        }
    }
}