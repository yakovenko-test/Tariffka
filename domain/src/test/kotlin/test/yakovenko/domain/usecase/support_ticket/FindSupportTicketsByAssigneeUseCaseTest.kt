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

class FindSupportTicketsByAssigneeUseCaseTest {
    private lateinit var findSupportTicketsByAssigneeUseCase: FindSupportTicketsByAssigneeUseCase

    private val supportTicketsRepository: SupportTicketsRepository = mockk()
    private val usersRepository: UsersRepository = mockk()

    private val assigneeId = UUID.randomUUID()

    @BeforeEach
    fun setUp() {
        findSupportTicketsByAssigneeUseCase = FindSupportTicketsByAssigneeUseCase(
            supportTicketsRepository, usersRepository
        )
    }

    @Test
    fun `should find support tickets for existing assignee`() = runTest {
        // Arrange
        val supportTickets = listOf(
            TestSupportTicket.create(
                reporterId = UUID.randomUUID(),
                assigneeId = assigneeId
            ),
            TestSupportTicket.create(
                reporterId = UUID.randomUUID(),
                assigneeId = assigneeId
            )
        )

        coEvery { usersRepository.exists(assigneeId) } returns true
        coEvery { supportTicketsRepository.findByAssignee(assigneeId) } returns supportTickets

        // Act
        val result = findSupportTicketsByAssigneeUseCase(assigneeId)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(supportTickets, result.getOrNull())

        coVerifySequence {
            usersRepository.exists(assigneeId)
            supportTicketsRepository.findByAssignee(assigneeId)
        }
    }

    @Test
    fun `should return empty collection when assignee has no support tickets`() = runTest {
        // Arrange
        coEvery { usersRepository.exists(assigneeId) } returns true
        coEvery {
            supportTicketsRepository.findByAssignee(assigneeId)
        } returns emptyList<SupportTicket>()

        // Act
        val result = findSupportTicketsByAssigneeUseCase(assigneeId)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(emptyList<SupportTicket>(), result.getOrNull())

        coVerifySequence {
            usersRepository.exists(assigneeId)
            supportTicketsRepository.findByAssignee(assigneeId)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when assignee does not exist`() = runTest {
        // Arrange
        coEvery { usersRepository.exists(assigneeId) } returns false

        // Act
        val result = findSupportTicketsByAssigneeUseCase(assigneeId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerify(exactly = 1) { usersRepository.exists(assigneeId) }
        coVerify(exactly = 0) { supportTicketsRepository.findByAssignee(any()) }
    }
}