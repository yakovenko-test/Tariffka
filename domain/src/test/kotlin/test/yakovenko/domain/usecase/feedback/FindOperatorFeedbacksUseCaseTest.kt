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
import test.yakovenko.domain.model.Feedback.OperatorFeedback
import test.yakovenko.domain.model.TestFeedback
import test.yakovenko.domain.repository.FeedbacksRepository
import test.yakovenko.domain.repository.OperatorsRepository
import java.util.UUID

class FindOperatorFeedbacksUseCaseTest {
    private lateinit var findOperatorFeedbacksUseCase: FindOperatorFeedbacksUseCase

    private val feedbacksRepository: FeedbacksRepository = mockk()
    private val operatorsRepository: OperatorsRepository = mockk()

    private val operatorId = UUID.randomUUID()

    @BeforeEach
    fun setUp() {
        findOperatorFeedbacksUseCase = FindOperatorFeedbacksUseCase(
            feedbacksRepository, operatorsRepository
        )
    }

    @Test
    fun `should find feedbacks for existing operator`() = runTest {
        // Arrange
        val feedbacks = listOf(
            TestFeedback.createOperatorFeedback(
                authorId = UUID.randomUUID(),
                operatorId = operatorId
            ),
            TestFeedback.createOperatorFeedback(
                authorId = UUID.randomUUID(),
                operatorId = operatorId
            )
        )

        coEvery { operatorsRepository.exists(operatorId) } returns true
        coEvery { feedbacksRepository.findForOperator(operatorId) } returns feedbacks

        // Act
        val result = findOperatorFeedbacksUseCase(operatorId)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(feedbacks, result.getOrNull())

        coVerifySequence {
            operatorsRepository.exists(operatorId)
            feedbacksRepository.findForOperator(operatorId)
        }
    }

    @Test
    fun `should return empty collection when no operator feedbacks exist`() = runTest {
        // Arrange
        coEvery { operatorsRepository.exists(operatorId) } returns true
        coEvery {
            feedbacksRepository.findForOperator(operatorId)
        } returns emptyList<OperatorFeedback>()

        // Act
        val result = findOperatorFeedbacksUseCase(operatorId)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(emptyList<OperatorFeedback>(), result.getOrNull())

        coVerifySequence {
            operatorsRepository.exists(operatorId)
            feedbacksRepository.findForOperator(operatorId)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when operator does not exist`() = runTest {
        // Arrange
        coEvery { operatorsRepository.exists(operatorId) } returns false

        // Act
        val result = findOperatorFeedbacksUseCase(operatorId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerify(exactly = 1) { operatorsRepository.exists(operatorId) }
        coVerify(exactly = 0) { feedbacksRepository.findForOperator(any()) }
    }
}