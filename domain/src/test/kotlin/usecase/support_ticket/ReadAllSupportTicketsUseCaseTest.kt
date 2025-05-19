package usecase.support_ticket

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import model.SupportTicket
import model.TestSupportTicket
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import repository.SupportTicketsRepository
import java.util.*

class ReadAllSupportTicketsUseCaseTest {
    private lateinit var readAllSupportTicketsUseCase: ReadAllSupportTicketsUseCase

    private val supportTicketsRepository: SupportTicketsRepository = mockk()

    @BeforeEach
    fun setUp() {
        readAllSupportTicketsUseCase = ReadAllSupportTicketsUseCase(supportTicketsRepository)
    }

    @Test
    fun `should read all support tickets successfully`() = runTest {
        // Arrange
        val supportTickets = listOf(
            TestSupportTicket.create(reporterId = UUID.randomUUID()),
            TestSupportTicket.create(reporterId = UUID.randomUUID())
        )

        coEvery { supportTicketsRepository.readAll() } returns supportTickets

        // Act
        val result = readAllSupportTicketsUseCase()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(supportTickets, result.getOrNull())

        coVerify(exactly = 1) { supportTicketsRepository.readAll() }
    }

    @Test
    fun `should return empty collection when no support tickets exist`() = runTest {
        // Arrange
        coEvery { supportTicketsRepository.readAll() } returns emptyList<SupportTicket>()

        // Act
        val result = readAllSupportTicketsUseCase()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(emptyList<SupportTicket>(), result.getOrNull())

        coVerify(exactly = 1) { supportTicketsRepository.readAll() }
    }
}