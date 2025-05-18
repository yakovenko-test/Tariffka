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
import test.yakovenko.domain.model.Feedback.TariffFeedback
import test.yakovenko.domain.model.TestFeedback
import test.yakovenko.domain.repository.FeedbacksRepository
import test.yakovenko.domain.repository.TariffsRepository
import java.util.UUID

class FindTariffFeedbacksUseCaseTest {
    private lateinit var findTariffFeedbacksUseCase: FindTariffFeedbacksUseCase

    private val feedbacksRepository: FeedbacksRepository = mockk()
    private val tariffsRepository: TariffsRepository = mockk()

    private val tariffId = UUID.randomUUID()

    @BeforeEach
    fun setUp() {
        findTariffFeedbacksUseCase = FindTariffFeedbacksUseCase(
            feedbacksRepository, tariffsRepository
        )
    }

    @Test
    fun `should find feedbacks for existing tariff`() = runTest {
        // Arrange
        val feedbacks = listOf(
            TestFeedback.createTariffFeedback(authorId = UUID.randomUUID(), tariffId = tariffId),
            TestFeedback.createTariffFeedback(authorId = UUID.randomUUID(), tariffId = tariffId)
        )

        coEvery { tariffsRepository.exists(tariffId) } returns true
        coEvery { feedbacksRepository.findForTariff(tariffId) } returns feedbacks

        // Act
        val result = findTariffFeedbacksUseCase(tariffId)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(feedbacks, result.getOrNull())

        coVerifySequence {
            tariffsRepository.exists(tariffId)
            feedbacksRepository.findForTariff(tariffId)
        }
    }

    @Test
    fun `should return empty collection when no tariff feedbacks exist`() = runTest {
        // Arrange
        coEvery { tariffsRepository.exists(tariffId) } returns true
        coEvery { feedbacksRepository.findForTariff(tariffId) } returns emptyList<TariffFeedback>()

        // Act
        val result = findTariffFeedbacksUseCase(tariffId)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(emptyList<TariffFeedback>(), result.getOrNull())

        coVerifySequence {
            tariffsRepository.exists(tariffId)
            feedbacksRepository.findForTariff(tariffId)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when tariff does not exist`() = runTest {
        // Arrange
        coEvery { tariffsRepository.exists(tariffId) } returns false

        // Act
        val result = findTariffFeedbacksUseCase(tariffId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerify(exactly = 1) { tariffsRepository.exists(tariffId) }
        coVerify(exactly = 0) { feedbacksRepository.findForTariff(any()) }
    }
}
