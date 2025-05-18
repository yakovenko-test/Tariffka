package test.yakovenko.domain.usecase.feedback.create

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import test.yakovenko.domain.exception.ModelCreateException
import test.yakovenko.domain.exception.ModelDuplicateException
import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.model.TestFeedback
import test.yakovenko.domain.repository.FeedbacksRepository
import test.yakovenko.domain.repository.OperatorsRepository
import test.yakovenko.domain.repository.UsersRepository
import java.util.UUID

class CreateOperatorFeedbackUseCaseTest {
    private lateinit var createOperatorFeedbackUseCase: CreateOperatorFeedbackUseCase

    private val feedbacksRepository: FeedbacksRepository = mockk()
    private val usersRepository: UsersRepository = mockk()
    private val operatorsRepository: OperatorsRepository = mockk()

    private val operatorFeedback = TestFeedback.createOperatorFeedback(
        authorId = UUID.randomUUID(),
        operatorId = UUID.randomUUID()
    )

    @BeforeEach
    fun setUp() {
        createOperatorFeedbackUseCase = CreateOperatorFeedbackUseCase(
            feedbacksRepository,
            usersRepository,
            operatorsRepository
        )
    }

    @Test
    fun `should create operator feedback successfully`() = runTest {
        // Arrange
        coEvery { feedbacksRepository.exists(operatorFeedback.id) } returns false
        coEvery { usersRepository.exists(operatorFeedback.authorId) } returns true
        coEvery { operatorsRepository.exists(operatorFeedback.operatorId) } returns true
        coEvery { feedbacksRepository.create(operatorFeedback) } returns operatorFeedback

        // Act
        val result = createOperatorFeedbackUseCase(operatorFeedback)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(operatorFeedback, result.getOrNull())

        coVerifySequence {
            feedbacksRepository.exists(operatorFeedback.id)
            usersRepository.exists(operatorFeedback.authorId)
            operatorsRepository.exists(operatorFeedback.operatorId)
            feedbacksRepository.create(operatorFeedback)
        }
    }

    @Test
    fun `should fail with ModelDuplicateException when operator feedback already exists`() =
        runTest {
            // Arrange
            coEvery { feedbacksRepository.exists(operatorFeedback.id) } returns true

            // Act
            val result = createOperatorFeedbackUseCase(operatorFeedback)

            // Assert
            assertTrue(result.isFailure)
            assertTrue(result.exceptionOrNull() is ModelDuplicateException)

            coVerify(exactly = 1) { feedbacksRepository.exists(operatorFeedback.id) }
            coVerify(exactly = 0) { usersRepository.exists(any()) }
            coVerify(exactly = 0) { operatorsRepository.exists(any()) }
            coVerify(exactly = 0) { feedbacksRepository.create(any()) }
        }

    @Test
    fun `should fail with ModelNotFoundException when author does not exist`() = runTest {
        // Arrange
        coEvery { feedbacksRepository.exists(operatorFeedback.id) } returns false
        coEvery { usersRepository.exists(operatorFeedback.authorId) } returns false

        // Act
        val result = createOperatorFeedbackUseCase(operatorFeedback)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerifySequence {
            feedbacksRepository.exists(operatorFeedback.id)
            usersRepository.exists(operatorFeedback.authorId)
        }
        coVerify(exactly = 0) { operatorsRepository.exists(any()) }
        coVerify(exactly = 0) { feedbacksRepository.create(any()) }
    }

    @Test
    fun `should fail with ModelNotFoundException when operator does not exist`() = runTest {
        // Arrange
        coEvery { feedbacksRepository.exists(operatorFeedback.id) } returns false
        coEvery { usersRepository.exists(operatorFeedback.authorId) } returns true
        coEvery { operatorsRepository.exists(operatorFeedback.operatorId) } returns false

        // Act
        val result = createOperatorFeedbackUseCase(operatorFeedback)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerifySequence {
            feedbacksRepository.exists(operatorFeedback.id)
            usersRepository.exists(operatorFeedback.authorId)
            operatorsRepository.exists(operatorFeedback.operatorId)
        }
        coVerify(exactly = 0) { feedbacksRepository.create(any()) }
    }

    @Test
    fun `should fail with ModelCreateException when create returns null`() = runTest {
        // Arrange
        coEvery { feedbacksRepository.exists(operatorFeedback.id) } returns false
        coEvery { usersRepository.exists(operatorFeedback.authorId) } returns true
        coEvery { operatorsRepository.exists(operatorFeedback.operatorId) } returns true
        coEvery { feedbacksRepository.create(operatorFeedback) } returns null

        // Act
        val result = createOperatorFeedbackUseCase(operatorFeedback)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelCreateException)

        coVerifySequence {
            feedbacksRepository.exists(operatorFeedback.id)
            usersRepository.exists(operatorFeedback.authorId)
            operatorsRepository.exists(operatorFeedback.operatorId)
            feedbacksRepository.create(operatorFeedback)
        }
    }
}
