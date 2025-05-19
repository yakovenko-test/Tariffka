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
import repository.ServicesRepository
import repository.UsersRepository
import java.util.*

class CreateServiceFeedbackUseCaseTest {
    private lateinit var createServiceFeedbackUseCase: CreateServiceFeedbackUseCase

    private val feedbacksRepository: FeedbacksRepository = mockk()
    private val usersRepository: UsersRepository = mockk()
    private val servicesRepository: ServicesRepository = mockk()

    private val serviceFeedback = TestFeedback.createServiceFeedback(
        authorId = UUID.randomUUID(),
        serviceId = UUID.randomUUID()
    )

    @BeforeEach
    fun setUp() {
        createServiceFeedbackUseCase = CreateServiceFeedbackUseCase(
            feedbacksRepository,
            usersRepository,
            servicesRepository
        )
    }

    @Test
    fun `should create service feedback successfully`() = runTest {
        // Arrange
        coEvery { feedbacksRepository.exists(serviceFeedback.id) } returns false
        coEvery { usersRepository.exists(serviceFeedback.authorId) } returns true
        coEvery { servicesRepository.exists(serviceFeedback.serviceId) } returns true
        coEvery { feedbacksRepository.create(serviceFeedback) } returns serviceFeedback

        // Act
        val result = createServiceFeedbackUseCase(serviceFeedback)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(serviceFeedback, result.getOrNull())

        coVerifySequence {
            feedbacksRepository.exists(serviceFeedback.id)
            usersRepository.exists(serviceFeedback.authorId)
            servicesRepository.exists(serviceFeedback.serviceId)
            feedbacksRepository.create(serviceFeedback)
        }
    }

    @Test
    fun `should fail with ModelDuplicateException when service feedback already exists`() =
        runTest {
            // Arrange
            coEvery { feedbacksRepository.exists(serviceFeedback.id) } returns true

            // Act
            val result = createServiceFeedbackUseCase(serviceFeedback)

            // Assert
            assertTrue(result.isFailure)
            assertTrue(result.exceptionOrNull() is ModelDuplicateException)

            coVerify(exactly = 1) { feedbacksRepository.exists(serviceFeedback.id) }
            coVerify(exactly = 0) { usersRepository.exists(any()) }
            coVerify(exactly = 0) { servicesRepository.exists(any()) }
            coVerify(exactly = 0) { feedbacksRepository.create(any()) }
        }

    @Test
    fun `should fail with ModelNotFoundException when author does not exist`() = runTest {
        // Arrange
        coEvery { feedbacksRepository.exists(serviceFeedback.id) } returns false
        coEvery { usersRepository.exists(serviceFeedback.authorId) } returns false

        // Act
        val result = createServiceFeedbackUseCase(serviceFeedback)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerifySequence {
            feedbacksRepository.exists(serviceFeedback.id)
            usersRepository.exists(serviceFeedback.authorId)
        }
        coVerify(exactly = 0) { servicesRepository.exists(any()) }
        coVerify(exactly = 0) { feedbacksRepository.create(any()) }
    }

    @Test
    fun `should fail with ModelNotFoundException when service does not exist`() = runTest {
        // Arrange
        coEvery { feedbacksRepository.exists(serviceFeedback.id) } returns false
        coEvery { usersRepository.exists(serviceFeedback.authorId) } returns true
        coEvery { servicesRepository.exists(serviceFeedback.serviceId) } returns false

        // Act
        val result = createServiceFeedbackUseCase(serviceFeedback)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerifySequence {
            feedbacksRepository.exists(serviceFeedback.id)
            usersRepository.exists(serviceFeedback.authorId)
            servicesRepository.exists(serviceFeedback.serviceId)
        }
        coVerify(exactly = 0) { feedbacksRepository.create(any()) }
    }

    @Test
    fun `should fail with ModelCreateException when create returns null`() = runTest {
        // Arrange
        coEvery { feedbacksRepository.exists(serviceFeedback.id) } returns false
        coEvery { usersRepository.exists(serviceFeedback.authorId) } returns true
        coEvery { servicesRepository.exists(serviceFeedback.serviceId) } returns true
        coEvery { feedbacksRepository.create(serviceFeedback) } returns null

        // Act
        val result = createServiceFeedbackUseCase(serviceFeedback)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelCreateException)

        coVerifySequence {
            feedbacksRepository.exists(serviceFeedback.id)
            usersRepository.exists(serviceFeedback.authorId)
            servicesRepository.exists(serviceFeedback.serviceId)
            feedbacksRepository.create(serviceFeedback)
        }
    }
}
