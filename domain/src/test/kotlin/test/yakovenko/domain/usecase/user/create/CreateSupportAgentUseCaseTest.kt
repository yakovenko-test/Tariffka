package test.yakovenko.domain.usecase.user.create

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import test.yakovenko.domain.exception.ModelCreateException
import test.yakovenko.domain.exception.ModelDuplicateException
import test.yakovenko.domain.model.TestUser
import test.yakovenko.domain.repository.UsersRepository

class CreateSupportAgentUseCaseTest {
    private lateinit var createSupportAgentUseCase: CreateSupportAgentUseCase

    private val usersRepository: UsersRepository = mockk()

    private val supportAgent = TestUser.createSupportAgent()

    @BeforeEach
    fun setUp() {
        createSupportAgentUseCase = CreateSupportAgentUseCase(usersRepository)
    }

    @Test
    fun `should create support agent successfully`() = runTest {
        // Arrange
        coEvery { usersRepository.exists(supportAgent.id) } returns false
        coEvery { usersRepository.create(supportAgent) } returns supportAgent

        // Act
        val result = createSupportAgentUseCase(supportAgent)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(supportAgent, result.getOrNull())

        coVerifySequence {
            usersRepository.exists(supportAgent.id)
            usersRepository.create(supportAgent)
        }
    }

    @Test
    fun `should fail with ModelDuplicateException when support agent already exists`() = runTest {
        // Arrange
        coEvery { usersRepository.exists(supportAgent.id) } returns true

        // Act
        val result = createSupportAgentUseCase(supportAgent)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelDuplicateException)

        coVerify(exactly = 1) { usersRepository.exists(supportAgent.id) }
        coVerify(exactly = 0) { usersRepository.create(any()) }
    }

    @Test
    fun `should fail with ModelCreateException when create returns null`() = runTest {
        // Arrange
        coEvery { usersRepository.exists(supportAgent.id) } returns false
        coEvery { usersRepository.create(supportAgent) } returns null

        // Act
        val result = createSupportAgentUseCase(supportAgent)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelCreateException)

        coVerifySequence {
            usersRepository.exists(supportAgent.id)
            usersRepository.create(supportAgent)
        }
    }
}