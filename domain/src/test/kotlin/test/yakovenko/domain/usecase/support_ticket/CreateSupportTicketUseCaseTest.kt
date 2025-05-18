package test.yakovenko.domain.usecase.support_ticket

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import test.yakovenko.domain.exception.ModelCreateException
import test.yakovenko.domain.exception.ModelDuplicateException
import test.yakovenko.domain.exception.ModelNotFoundException
import test.yakovenko.domain.model.TestSupportTicket
import test.yakovenko.domain.repository.SupportTicketsRepository
import test.yakovenko.domain.repository.UsersRepository
import java.util.UUID

class CreateSupportTicketUseCaseTest {
    private lateinit var createSupportTicketUseCase: CreateSupportTicketUseCase

    private val supportTicketsRepository: SupportTicketsRepository = mockk()
    private val usersRepository: UsersRepository = mockk()

    private val supportTicket = TestSupportTicket.create(
        reporterId = UUID.randomUUID(),
        assigneeId = UUID.randomUUID()
    )

    @BeforeEach
    fun setUp() {
        createSupportTicketUseCase = CreateSupportTicketUseCase(
            supportTicketsRepository, usersRepository
        )
    }

    @Test
    fun `should create support ticket successfully`() = runTest {
        // Arrange
        coEvery { supportTicketsRepository.exists(supportTicket.id) } returns false
        coEvery { usersRepository.exists(supportTicket.reporterId) } returns true
        supportTicket.assigneeId?.let {
            coEvery { usersRepository.exists(supportTicket.assigneeId) } returns true
        }
        coEvery { supportTicketsRepository.create(supportTicket) } returns supportTicket

        // Act
        val result = createSupportTicketUseCase(supportTicket)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(supportTicket, result.getOrNull())

        coVerifySequence {
            supportTicketsRepository.exists(supportTicket.id)
            usersRepository.exists(supportTicket.reporterId)
            supportTicket.assigneeId?.let { usersRepository.exists(supportTicket.assigneeId) }
            supportTicketsRepository.create(supportTicket)
        }
    }

    @Test
    fun `should fail with ModelDuplicateException when support ticket already exists`() = runTest {
        // Arrange
        coEvery { supportTicketsRepository.exists(supportTicket.id) } returns true

        // Act
        val result = createSupportTicketUseCase(supportTicket)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelDuplicateException)

        coVerify(exactly = 1) { supportTicketsRepository.exists(supportTicket.id) }
        coVerify(exactly = 0) { usersRepository.exists(any()) }
        coVerify(exactly = 0) { supportTicketsRepository.create(any()) }
    }

    @Test
    fun `should fail with ModelNotFoundException when reporter does not exist`() = runTest {
        // Arrange
        coEvery { supportTicketsRepository.exists(supportTicket.id) } returns false
        coEvery { usersRepository.exists(supportTicket.reporterId) } returns false

        // Act
        val result = createSupportTicketUseCase(supportTicket)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerifySequence {
            supportTicketsRepository.exists(supportTicket.id)
            usersRepository.exists(supportTicket.reporterId)
        }
        coVerify(exactly = 0) { supportTicketsRepository.create(any()) }
    }

    @Test
    fun `should fail with ModelNotFoundException when assignee does not exist`() = runTest {
        assumeTrue(supportTicket.assigneeId != null)

        // Arrange
        coEvery { supportTicketsRepository.exists(supportTicket.id) } returns false
        coEvery { usersRepository.exists(supportTicket.reporterId) } returns true
        coEvery { usersRepository.exists(supportTicket.assigneeId!!) } returns false

        // Act
        val result = createSupportTicketUseCase(supportTicket)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerifySequence {
            supportTicketsRepository.exists(supportTicket.id)
            usersRepository.exists(supportTicket.reporterId)
            usersRepository.exists(supportTicket.assigneeId!!)
        }
        coVerify(exactly = 0) { supportTicketsRepository.create(any()) }
    }

    @Test
    fun `should fail with ModelCreateException when create returns null`() = runTest {
        // Arrange
        coEvery { supportTicketsRepository.exists(supportTicket.id) } returns false
        coEvery { usersRepository.exists(supportTicket.reporterId) } returns true
        supportTicket.assigneeId?.let {
            coEvery { usersRepository.exists(supportTicket.assigneeId) } returns true
        }
        coEvery { supportTicketsRepository.create(supportTicket) } returns null

        // Act
        val result = createSupportTicketUseCase(supportTicket)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelCreateException)

        coVerifySequence {
            supportTicketsRepository.exists(supportTicket.id)
            usersRepository.exists(supportTicket.reporterId)
            supportTicket.assigneeId?.let { usersRepository.exists(supportTicket.assigneeId) }
            supportTicketsRepository.create(supportTicket)
        }
    }
}
