package test.yakovenko.domain.usecase.user

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
import test.yakovenko.domain.model.TestUser
import test.yakovenko.domain.repository.UsersRepository
import java.util.UUID

class ReadUserUseCaseTest {
    private lateinit var readUserUseCase: ReadUserUseCase

    private val usersRepository: UsersRepository = mockk()

    private val userId = UUID.randomUUID()

    @BeforeEach
    fun setUp() {
        readUserUseCase = ReadUserUseCase(usersRepository)
    }

    @Test
    fun `should return user successfully`() = runTest {
        // Arrange
        val user = TestUser.createRegularUser()

        coEvery { usersRepository.exists(userId) } returns true
        coEvery { usersRepository.read(userId) } returns user

        // Act
        val result = readUserUseCase(userId)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(user, result.getOrNull())

        coVerifySequence {
            usersRepository.exists(userId)
            usersRepository.read(userId)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when user does not exist`() = runTest {
        // Arrange
        coEvery { usersRepository.exists(userId) } returns false

        // Act
        val result = readUserUseCase(userId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerify(exactly = 1) { usersRepository.exists(userId) }
        coVerify(exactly = 0) { usersRepository.read(any()) }
    }

    @Test
    fun `should fail with ModelReadException when read returns null`() = runTest {
        // Arrange
        coEvery { usersRepository.exists(userId) } returns true
        coEvery { usersRepository.read(userId) } returns null

        // Act
        val result = readUserUseCase(userId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelReadException)

        coVerifySequence {
            usersRepository.exists(userId)
            usersRepository.read(userId)
        }
    }
}