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
import test.yakovenko.domain.exception.ModelReadException
import test.yakovenko.domain.model.TestFeedback
import test.yakovenko.domain.repository.FeedbacksRepository
import java.util.UUID

class ReadFeedbackUseCaseTest {
    private lateinit var readFeedbackUseCase: ReadFeedbackUseCase

    private val feedbacksRepository: FeedbacksRepository = mockk()

    private val feedbackId = UUID.randomUUID()

    @BeforeEach
    fun setUp() {
        readFeedbackUseCase = ReadFeedbackUseCase(feedbacksRepository)
    }

    @Test
    fun `should read feedback successfully`() = runTest {
        // Arrange
        val feedback = TestFeedback.createOperatorFeedback(
            id = feedbackId,
            authorId = UUID.randomUUID(),
            operatorId = UUID.randomUUID()
        )

        coEvery { feedbacksRepository.exists(feedbackId) } returns true
        coEvery { feedbacksRepository.read(feedbackId) } returns feedback

        // Act
        val result = readFeedbackUseCase(feedbackId)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(feedback, result.getOrNull())

        coVerifySequence {
            feedbacksRepository.exists(feedbackId)
            feedbacksRepository.read(feedbackId)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when feedback does not exist`() = runTest {
        // Arrange
        coEvery { feedbacksRepository.exists(feedbackId) } returns false

        // Act
        val result = readFeedbackUseCase(feedbackId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerify(exactly = 1) { feedbacksRepository.exists(feedbackId) }
        coVerify(exactly = 0) { feedbacksRepository.read(any()) }
    }

    @Test
    fun `should fail with ModelReadException when read returns null`() = runTest {
        // Arrange
        coEvery { feedbacksRepository.exists(feedbackId) } returns true
        coEvery { feedbacksRepository.read(feedbackId) } returns null

        // Act
        val result = readFeedbackUseCase(feedbackId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelReadException)

        coVerifySequence {
            feedbacksRepository.exists(feedbackId)
            feedbacksRepository.read(feedbackId)
        }
    }
}