package test.yakovenko.domain.usecase.support_ticket

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
import test.yakovenko.domain.exception.ModelReadException
import test.yakovenko.domain.model.TestSupportTicket
import test.yakovenko.domain.repository.SupportTicketsRepository
import java.util.UUID

class ReadSupportTicketUseCaseTest {
    private lateinit var readSupportTicketUseCase: ReadSupportTicketUseCase

    private val supportTicketsRepository: SupportTicketsRepository = mockk()

    private val supportTicketId = UUID.randomUUID()

    @BeforeEach
    fun setUp() {
        readSupportTicketUseCase = ReadSupportTicketUseCase(supportTicketsRepository)
    }

    @Test
    fun `should return read ticket successfully`() = runTest {
        // Arrange
        val supportTicket = TestSupportTicket.create(
            id = supportTicketId,
            reporterId = UUID.randomUUID()
        )

        coEvery { supportTicketsRepository.exists(supportTicketId) } returns true
        coEvery { supportTicketsRepository.read(supportTicketId) } returns supportTicket

        // Act
        val result = readSupportTicketUseCase(supportTicketId)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(supportTicket, result.getOrNull())

        coVerifySequence {
            supportTicketsRepository.exists(supportTicketId)
            supportTicketsRepository.read(supportTicketId)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when support ticket does not exist`() = runTest {
        // Arrange
        coEvery { supportTicketsRepository.exists(supportTicketId) } returns false

        // Act
        val result = readSupportTicketUseCase(supportTicketId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerify(exactly = 1) { supportTicketsRepository.exists(supportTicketId) }
        coVerify(exactly = 0) { supportTicketsRepository.read(any()) }
    }

    @Test
    fun `should fail with ModelReadException when read returns null`() = runTest {
        // Arrange
        coEvery { supportTicketsRepository.exists(supportTicketId) } returns true
        coEvery { supportTicketsRepository.read(supportTicketId) } returns null

        // Act
        val result = readSupportTicketUseCase(supportTicketId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelReadException)

        coVerifySequence {
            supportTicketsRepository.exists(supportTicketId)
            supportTicketsRepository.read(supportTicketId)
        }
    }
}