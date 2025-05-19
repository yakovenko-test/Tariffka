package usecase.support_ticket

import exception.ModelDeleteException
import exception.ModelNotFoundException
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import repository.SupportTicketsRepository
import java.util.*

class DeleteSupportTicketUseCaseTest {
    private lateinit var deleteSupportTicketUseCase: DeleteSupportTicketUseCase

    private val supportTicketsRepository: SupportTicketsRepository = mockk()

    private val supportTicketId = UUID.randomUUID()

    @BeforeEach
    fun setUp() {
        deleteSupportTicketUseCase = DeleteSupportTicketUseCase(supportTicketsRepository)
    }

    @Test
    fun `should delete support ticket successfully`() = runTest {
        // Arrange
        coEvery { supportTicketsRepository.exists(supportTicketId) } returns true
        coEvery { supportTicketsRepository.delete(supportTicketId) } returns true

        // Act
        val result = deleteSupportTicketUseCase(supportTicketId)

        // Assert
        assertTrue(result.isSuccess)

        coVerifySequence {
            supportTicketsRepository.exists(supportTicketId)
            supportTicketsRepository.delete(supportTicketId)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when support ticket does not exist`() = runTest {
        // Arrange
        coEvery { supportTicketsRepository.exists(supportTicketId) } returns false

        // Act
        val result = deleteSupportTicketUseCase(supportTicketId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerify(exactly = 1) { supportTicketsRepository.exists(supportTicketId) }
        coVerify(exactly = 0) { supportTicketsRepository.delete(any()) }
    }

    @Test
    fun `should fail with ModelDeleteException when delete returns false`() = runTest {
        // Arrange
        coEvery { supportTicketsRepository.exists(supportTicketId) } returns true
        coEvery { supportTicketsRepository.delete(supportTicketId) } returns false

        // Act
        val result = deleteSupportTicketUseCase(supportTicketId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelDeleteException)

        coVerifySequence {
            supportTicketsRepository.exists(supportTicketId)
            supportTicketsRepository.delete(supportTicketId)
        }
    }
}