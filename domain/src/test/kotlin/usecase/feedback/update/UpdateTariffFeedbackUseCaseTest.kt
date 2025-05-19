package usecase.feedback.update

import exception.ModelNotFoundException
import exception.ModelUpdateException
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import model.TestFeedback
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import repository.FeedbacksRepository
import repository.TariffsRepository
import repository.UsersRepository
import java.util.*

class UpdateTariffFeedbackUseCaseTest {
    private lateinit var updateTariffFeedbackUseCase: UpdateTariffFeedbackUseCase

    private val feedbacksRepository: FeedbacksRepository = mockk()
    private val usersRepository: UsersRepository = mockk()
    private val tariffsRepository: TariffsRepository = mockk()

    private val tariffFeedback = TestFeedback.createTariffFeedback(
        authorId = UUID.randomUUID(),
        tariffId = UUID.randomUUID()
    )

    @BeforeEach
    fun setUp() {
        updateTariffFeedbackUseCase = UpdateTariffFeedbackUseCase(
            feedbacksRepository,
            usersRepository,
            tariffsRepository
        )
    }

    @Test
    fun `should update tariff feedback successfully`() = runTest {
        // Arrange
        coEvery { feedbacksRepository.exists(tariffFeedback.id) } returns true
        coEvery { usersRepository.exists(tariffFeedback.authorId) } returns true
        coEvery { tariffsRepository.exists(tariffFeedback.tariffId) } returns true
        coEvery { feedbacksRepository.update(tariffFeedback) } returns tariffFeedback

        // Act
        val result = updateTariffFeedbackUseCase(tariffFeedback)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(tariffFeedback, result.getOrNull())

        coVerifySequence {
            feedbacksRepository.exists(tariffFeedback.id)
            usersRepository.exists(tariffFeedback.authorId)
            tariffsRepository.exists(tariffFeedback.tariffId)
            feedbacksRepository.update(tariffFeedback)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when tariff feedback does not exist`() = runTest {
        // Arrange
        coEvery { feedbacksRepository.exists(tariffFeedback.id) } returns false

        // Act
        val result = updateTariffFeedbackUseCase(tariffFeedback)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerify(exactly = 1) { feedbacksRepository.exists(tariffFeedback.id) }
        coVerify(exactly = 0) { usersRepository.exists(any()) }
        coVerify(exactly = 0) { tariffsRepository.exists(any()) }
        coVerify(exactly = 0) { feedbacksRepository.update(any()) }
    }

    @Test
    fun `should fail with ModelNotFoundException when author does not exist`() = runTest {
        // Arrange
        coEvery { feedbacksRepository.exists(tariffFeedback.id) } returns true
        coEvery { usersRepository.exists(tariffFeedback.authorId) } returns false

        // Act
        val result = updateTariffFeedbackUseCase(tariffFeedback)

        // Assert
        assert(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerifySequence {
            feedbacksRepository.exists(tariffFeedback.id)
            usersRepository.exists(tariffFeedback.authorId)
        }
        coVerify(exactly = 0) { tariffsRepository.exists(any()) }
        coVerify(exactly = 0) { feedbacksRepository.update(any()) }
    }

    @Test
    fun `should fail with ModelNotFoundException when tariff does not exist`() = runTest {
        // Arrange
        coEvery { feedbacksRepository.exists(tariffFeedback.id) } returns true
        coEvery { usersRepository.exists(tariffFeedback.authorId) } returns true
        coEvery { tariffsRepository.exists(tariffFeedback.tariffId) } returns false

        // Act
        val result = updateTariffFeedbackUseCase(tariffFeedback)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerifySequence {
            feedbacksRepository.exists(tariffFeedback.id)
            usersRepository.exists(tariffFeedback.authorId)
            tariffsRepository.exists(tariffFeedback.tariffId)
        }
        coVerify(exactly = 0) { feedbacksRepository.update(any()) }
    }

    @Test
    fun `should fail with ModelUpdateException when update returns null`() = runTest {
        // Arrange
        coEvery { feedbacksRepository.exists(tariffFeedback.id) } returns true
        coEvery { usersRepository.exists(tariffFeedback.authorId) } returns true
        coEvery { tariffsRepository.exists(tariffFeedback.tariffId) } returns true
        coEvery { feedbacksRepository.update(tariffFeedback) } returns null

        // Act
        val result = updateTariffFeedbackUseCase(tariffFeedback)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelUpdateException)

        coVerifySequence {
            feedbacksRepository.exists(tariffFeedback.id)
            usersRepository.exists(tariffFeedback.authorId)
            tariffsRepository.exists(tariffFeedback.tariffId)
            feedbacksRepository.update(tariffFeedback)
        }
    }
}
