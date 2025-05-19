package usecase.user.update

import exception.ModelNotFoundException
import exception.ModelUpdateException
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import model.TestUser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import repository.UsersRepository

class UpdateSupportAgentUseCaseTest {
    private lateinit var updateRegularUserUseCase: UpdateSupportAgentUseCase

    private val usersRepository: UsersRepository = mockk()

    private val supportAgent = TestUser.createSupportAgent()

    @BeforeEach
    fun setUp() {
        updateRegularUserUseCase = UpdateSupportAgentUseCase(usersRepository)
    }

    @Test
    fun `should update support agent successfully`() = runTest {
        // Arrange
        coEvery { usersRepository.exists(supportAgent.id) } returns true
        coEvery { usersRepository.update(supportAgent) } returns supportAgent

        // Act
        val result = updateRegularUserUseCase(supportAgent)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(supportAgent, result.getOrNull())

        coVerifySequence {
            usersRepository.exists(supportAgent.id)
            usersRepository.update(supportAgent)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when support agent does not exist`() = runTest {
        // Arrange
        coEvery { usersRepository.exists(supportAgent.id) } returns false

        // Act
        val result = updateRegularUserUseCase(supportAgent)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerify(exactly = 1) { usersRepository.exists(supportAgent.id) }
        coVerify(exactly = 0) { usersRepository.update(any()) }
    }

    @Test
    fun `should fail with ModelUpdateException when update returns null`() = runTest {
        // Arrange
        coEvery { usersRepository.exists(supportAgent.id) } returns true
        coEvery { usersRepository.update(supportAgent) } returns null

        // Act
        val result = updateRegularUserUseCase(supportAgent)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelUpdateException)

        coVerifySequence {
            usersRepository.exists(supportAgent.id)
            usersRepository.update(supportAgent)
        }
    }
}

