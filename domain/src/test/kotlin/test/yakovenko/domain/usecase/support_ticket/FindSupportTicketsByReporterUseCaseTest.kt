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
import test.yakovenko.domain.model.SupportTicket
import test.yakovenko.domain.model.TestSupportTicket
import test.yakovenko.domain.repository.SupportTicketsRepository
import test.yakovenko.domain.repository.UsersRepository
import java.util.UUID

class FindSupportTicketsByReporterUseCaseTest {
    private lateinit var findSupportTicketsByReporterUseCase: FindSupportTicketsByReporterUseCase

    private val supportTicketsRepository: SupportTicketsRepository = mockk()
    private val usersRepository: UsersRepository = mockk()

    private val reporterId = UUID.randomUUID()

    @BeforeEach
    fun setUp() {
        findSupportTicketsByReporterUseCase = FindSupportTicketsByReporterUseCase(
            supportTicketsRepository, usersRepository
        )
    }

    @Test
    fun `should find support tickets for existing reporter`() = runTest {
        // Arrange
        val supportTickets = listOf(
            TestSupportTicket.create(reporterId = reporterId),
            TestSupportTicket.create(reporterId = reporterId)
        )

        coEvery { usersRepository.exists(reporterId) } returns true
        coEvery { supportTicketsRepository.findByReporter(reporterId) } returns supportTickets

        // Act
        val result = findSupportTicketsByReporterUseCase(reporterId)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(supportTickets, result.getOrNull())

        coVerifySequence {
            usersRepository.exists(reporterId)
            supportTicketsRepository.findByReporter(reporterId)
        }
    }

    @Test
    fun `should return empty collection when reporter has no support tickets`() = runTest {
        // Arrange
        coEvery { usersRepository.exists(reporterId) } returns true
        coEvery {
            supportTicketsRepository.findByReporter(reporterId)
        } returns emptyList<SupportTicket>()

        // Act
        val result = findSupportTicketsByReporterUseCase(reporterId)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(emptyList<SupportTicket>(), result.getOrNull())

        coVerifySequence {
            usersRepository.exists(reporterId)
            supportTicketsRepository.findByReporter(reporterId)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when reporter does not exist`() = runTest {
        // Arrange
        coEvery { usersRepository.exists(reporterId) } returns false

        // Act
        val result = findSupportTicketsByReporterUseCase(reporterId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerify(exactly = 1) { usersRepository.exists(reporterId) }
        coVerify(exactly = 0) { supportTicketsRepository.findByReporter(any()) }
    }
}