package test.yakovenko.domain.usecase.feedback

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import test.yakovenko.domain.model.Feedback
import test.yakovenko.domain.model.TestFeedback
import test.yakovenko.domain.repository.FeedbacksRepository
import java.util.UUID

class ReadAllFeedbacksUseCaseTest {
    private lateinit var readAllFeedbacksUseCase: ReadAllFeedbacksUseCase

    private val feedbacksRepository: FeedbacksRepository = mockk()

    @BeforeEach
    fun setUp() {
        readAllFeedbacksUseCase = ReadAllFeedbacksUseCase(feedbacksRepository)
    }

    @Test
    fun `should read all feedbacks successfully`() = runTest {
        // Arrange
        val feedbacks = listOf(
            TestFeedback.createOperatorFeedback(
                authorId = UUID.randomUUID(),
                operatorId = UUID.randomUUID()
            ),
            TestFeedback.createOperatorFeedback(
                authorId = UUID.randomUUID(),
                operatorId = UUID.randomUUID()
            )
        )

        coEvery { feedbacksRepository.readAll() } returns feedbacks

        // Act
        val result = readAllFeedbacksUseCase()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(feedbacks, result.getOrNull())

        coVerify(exactly = 1) { feedbacksRepository.readAll() }
    }

    @Test
    fun `should return empty collection when no feedbacks exist`() = runTest {
        // Arrange
        coEvery { feedbacksRepository.readAll() } returns emptyList<Feedback>()

        // Act
        val result = readAllFeedbacksUseCase()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(emptyList<Feedback>(), result.getOrNull())

        coVerify(exactly = 1) { feedbacksRepository.readAll() }
    }
}