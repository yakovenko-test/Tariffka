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
import test.yakovenko.domain.model.Feedback
import test.yakovenko.domain.model.TestFeedback
import test.yakovenko.domain.repository.FeedbacksRepository
import test.yakovenko.domain.repository.UsersRepository
import java.util.UUID

class FindFeedbacksByAuthorUseCaseTest {
    private lateinit var findFeedbacksByAuthorUseCase: FindFeedbacksByAuthorUseCase

    private val feedbacksRepository: FeedbacksRepository = mockk()
    private val usersRepository: UsersRepository = mockk()

    private val authorId = UUID.randomUUID()

    @BeforeEach
    fun setUp() {
        findFeedbacksByAuthorUseCase = FindFeedbacksByAuthorUseCase(
            feedbacksRepository, usersRepository
        )
    }

    @Test
    fun `should find feedbacks for existing author`() = runTest {
        // Arrange
        val feedbacks = listOf(
            TestFeedback.createOperatorFeedback(
                authorId = authorId,
                operatorId = UUID.randomUUID()
            ),
            TestFeedback.createOperatorFeedback(
                authorId = authorId,
                operatorId = UUID.randomUUID()
            )
        )

        coEvery { usersRepository.exists(authorId) } returns true
        coEvery { feedbacksRepository.findByAuthor(authorId) } returns feedbacks

        // Act
        val result = findFeedbacksByAuthorUseCase(authorId)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(feedbacks, result.getOrNull())

        coVerifySequence {
            usersRepository.exists(authorId)
            feedbacksRepository.findByAuthor(authorId)
        }
    }

    @Test
    fun `should return empty collection when author has no feedbacks`() = runTest {
        // Arrange
        coEvery { usersRepository.exists(authorId) } returns true
        coEvery { feedbacksRepository.findByAuthor(authorId) } returns emptyList<Feedback>()

        // Act
        val result = findFeedbacksByAuthorUseCase(authorId)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(emptyList<Feedback>(), result.getOrNull())

        coVerifySequence {
            usersRepository.exists(authorId)
            feedbacksRepository.findByAuthor(authorId)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when author does not exist`() = runTest {
        // Arrange
        coEvery { usersRepository.exists(authorId) } returns false

        // Act
        val result = findFeedbacksByAuthorUseCase(authorId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerify(exactly = 1) { usersRepository.exists(authorId) }
        coVerify(exactly = 0) { feedbacksRepository.findByAuthor(any()) }
    }
}