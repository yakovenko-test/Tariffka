package test.yakovenko.domain.usecase.feedback.update

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
import test.yakovenko.domain.exception.ModelUpdateException
import test.yakovenko.domain.model.TestFeedback
import test.yakovenko.domain.repository.FeedbacksRepository
import test.yakovenko.domain.repository.OperatorsRepository
import test.yakovenko.domain.repository.UsersRepository
import java.util.UUID

class UpdateOperatorFeedbackUseCaseTest {
    private lateinit var updateOperatorFeedbackUseCase: UpdateOperatorFeedbackUseCase

    private val feedbacksRepository: FeedbacksRepository = mockk()
    private val usersRepository: UsersRepository = mockk()
    private val operatorsRepository: OperatorsRepository = mockk()

    private val operatorFeedback = TestFeedback.createOperatorFeedback(
        authorId = UUID.randomUUID(),
        operatorId = UUID.randomUUID()
    )

    @BeforeEach
    fun setUp() {
        updateOperatorFeedbackUseCase = UpdateOperatorFeedbackUseCase(
            feedbacksRepository,
            usersRepository,
            operatorsRepository
        )
    }

    @Test
    fun `should update operator feedback successfully`() = runTest {
        // Arrange
        coEvery { feedbacksRepository.exists(operatorFeedback.id) } returns true
        coEvery { usersRepository.exists(operatorFeedback.authorId) } returns true
        coEvery { operatorsRepository.exists(operatorFeedback.operatorId) } returns true
        coEvery { feedbacksRepository.update(operatorFeedback) } returns operatorFeedback

        // Act
        val result = updateOperatorFeedbackUseCase(operatorFeedback)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(operatorFeedback, result.getOrNull())

        coVerifySequence {
            feedbacksRepository.exists(operatorFeedback.id)
            usersRepository.exists(operatorFeedback.authorId)
            operatorsRepository.exists(operatorFeedback.operatorId)
            feedbacksRepository.update(operatorFeedback)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when operator feedback does not exist`() =
        runTest {
            // Arrange
            coEvery { feedbacksRepository.exists(operatorFeedback.id) } returns false

            // Act
            val result = updateOperatorFeedbackUseCase(operatorFeedback)

            // Assert
            assertTrue(result.isFailure)
            assertTrue(result.exceptionOrNull() is ModelNotFoundException)

            coVerify(exactly = 1) { feedbacksRepository.exists(operatorFeedback.id) }
            coVerify(exactly = 0) { usersRepository.exists(any()) }
            coVerify(exactly = 0) { operatorsRepository.exists(any()) }
            coVerify(exactly = 0) { feedbacksRepository.update(any()) }
        }

    @Test
    fun `should fail with ModelNotFoundException when author does not exist`() = runTest {
        // Arrange
        coEvery { feedbacksRepository.exists(operatorFeedback.id) } returns true
        coEvery { usersRepository.exists(operatorFeedback.authorId) } returns false

        // Act
        val result = updateOperatorFeedbackUseCase(operatorFeedback)

        // Assert
        assert(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerifySequence {
            feedbacksRepository.exists(operatorFeedback.id)
            usersRepository.exists(operatorFeedback.authorId)
        }
        coVerify(exactly = 0) { operatorsRepository.exists(any()) }
        coVerify(exactly = 0) { feedbacksRepository.update(any()) }
    }

    @Test
    fun `should fail with ModelNotFoundException when operator does not exist`() = runTest {
        // Arrange
        coEvery { feedbacksRepository.exists(operatorFeedback.id) } returns true
        coEvery { usersRepository.exists(operatorFeedback.authorId) } returns true
        coEvery { operatorsRepository.exists(operatorFeedback.operatorId) } returns false

        // Act
        val result = updateOperatorFeedbackUseCase(operatorFeedback)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerifySequence {
            feedbacksRepository.exists(operatorFeedback.id)
            usersRepository.exists(operatorFeedback.authorId)
            operatorsRepository.exists(operatorFeedback.operatorId)
        }
        coVerify(exactly = 0) { feedbacksRepository.update(any()) }
    }

    @Test
    fun `should fail with ModelUpdateException when update returns null`() = runTest {
        // Arrange
        coEvery { feedbacksRepository.exists(operatorFeedback.id) } returns true
        coEvery { usersRepository.exists(operatorFeedback.authorId) } returns true
        coEvery { operatorsRepository.exists(operatorFeedback.operatorId) } returns true
        coEvery { feedbacksRepository.update(operatorFeedback) } returns null

        // Act
        val result = updateOperatorFeedbackUseCase(operatorFeedback)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelUpdateException)

        coVerifySequence {
            feedbacksRepository.exists(operatorFeedback.id)
            usersRepository.exists(operatorFeedback.authorId)
            operatorsRepository.exists(operatorFeedback.operatorId)
            feedbacksRepository.update(operatorFeedback)
        }
    }
}
