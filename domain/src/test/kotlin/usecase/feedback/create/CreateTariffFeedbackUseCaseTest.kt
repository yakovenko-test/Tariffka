package usecase.feedback.create

import exception.ModelCreateException
import exception.ModelDuplicateException
import exception.ModelNotFoundException
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

class CreateTariffFeedbackUseCaseTest {
    private lateinit var createTariffFeedbackUseCase: CreateTariffFeedbackUseCase

    private val feedbacksRepository: FeedbacksRepository = mockk()
    private val usersRepository: UsersRepository = mockk()
    private val tariffsRepository: TariffsRepository = mockk()

    private val tariffFeedback = TestFeedback.createTariffFeedback(
        authorId = UUID.randomUUID(),
        tariffId = UUID.randomUUID()
    )

    @BeforeEach
    fun setUp() {
        createTariffFeedbackUseCase = CreateTariffFeedbackUseCase(
            feedbacksRepository,
            usersRepository,
            tariffsRepository
        )
    }

    @Test
    fun `should create tariff feedback successfully`() = runTest {
        // Arrange
        coEvery { feedbacksRepository.exists(tariffFeedback.id) } returns false
        coEvery { usersRepository.exists(tariffFeedback.authorId) } returns true
        coEvery { tariffsRepository.exists(tariffFeedback.tariffId) } returns true
        coEvery { feedbacksRepository.create(tariffFeedback) } returns tariffFeedback

        // Act
        val result = createTariffFeedbackUseCase(tariffFeedback)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(tariffFeedback, result.getOrNull())

        coVerifySequence {
            feedbacksRepository.exists(tariffFeedback.id)
            usersRepository.exists(tariffFeedback.authorId)
            tariffsRepository.exists(tariffFeedback.tariffId)
            feedbacksRepository.create(tariffFeedback)
        }
    }

    @Test
    fun `should fail with ModelDuplicateException when tariff feedback already exists`() = runTest {
        // Arrange
        coEvery { feedbacksRepository.exists(tariffFeedback.id) } returns true

        // Act
        val result = createTariffFeedbackUseCase(tariffFeedback)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelDuplicateException)

        coVerify(exactly = 1) { feedbacksRepository.exists(tariffFeedback.id) }
        coVerify(exactly = 0) { usersRepository.exists(any()) }
        coVerify(exactly = 0) { tariffsRepository.exists(any()) }
        coVerify(exactly = 0) { feedbacksRepository.create(any()) }
    }

    @Test
    fun `should fail with ModelNotFoundException when author does not exist`() = runTest {
        // Arrange
        coEvery { feedbacksRepository.exists(tariffFeedback.id) } returns false
        coEvery { usersRepository.exists(tariffFeedback.authorId) } returns false

        // Act
        val result = createTariffFeedbackUseCase(tariffFeedback)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerifySequence {
            feedbacksRepository.exists(tariffFeedback.id)
            usersRepository.exists(tariffFeedback.authorId)
        }
        coVerify(exactly = 0) { tariffsRepository.exists(any()) }
        coVerify(exactly = 0) { feedbacksRepository.create(any()) }
    }

    @Test
    fun `should fail with ModelNotFoundException when tariff does not exist`() = runTest {
        // Arrange
        coEvery { feedbacksRepository.exists(tariffFeedback.id) } returns false
        coEvery { usersRepository.exists(tariffFeedback.authorId) } returns true
        coEvery { tariffsRepository.exists(tariffFeedback.tariffId) } returns false

        // Act
        val result = createTariffFeedbackUseCase(tariffFeedback)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerifySequence {
            feedbacksRepository.exists(tariffFeedback.id)
            usersRepository.exists(tariffFeedback.authorId)
            tariffsRepository.exists(tariffFeedback.tariffId)
        }
        coVerify(exactly = 0) { feedbacksRepository.create(any()) }
    }

    @Test
    fun `should fail with ModelCreateException when create returns null`() = runTest {
        // Arrange
        coEvery { feedbacksRepository.exists(tariffFeedback.id) } returns false
        coEvery { usersRepository.exists(tariffFeedback.authorId) } returns true
        coEvery { tariffsRepository.exists(tariffFeedback.tariffId) } returns true
        coEvery { feedbacksRepository.create(tariffFeedback) } returns null

        // Act
        val result = createTariffFeedbackUseCase(tariffFeedback)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelCreateException)

        coVerifySequence {
            feedbacksRepository.exists(tariffFeedback.id)
            usersRepository.exists(tariffFeedback.authorId)
            tariffsRepository.exists(tariffFeedback.tariffId)
            feedbacksRepository.create(tariffFeedback)
        }
    }
}
