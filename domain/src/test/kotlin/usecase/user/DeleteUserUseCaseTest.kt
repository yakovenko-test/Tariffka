package usecase.user

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
import repository.UsersRepository
import java.util.*

class DeleteUserUseCaseTest {
    private lateinit var deleteUserUseCase: DeleteUserUseCase

    private val usersRepository: UsersRepository = mockk()

    private val userId = UUID.randomUUID()

    @BeforeEach
    fun setUp() {
        deleteUserUseCase = DeleteUserUseCase(usersRepository)
    }

    @Test
    fun `should delete user successfully`() = runTest {
        // Arrange
        coEvery { usersRepository.exists(userId) } returns true
        coEvery { usersRepository.delete(userId) } returns true

        // Act
        val result = deleteUserUseCase(userId)

        // Assert
        assertTrue(result.isSuccess)

        coVerifySequence {
            usersRepository.exists(userId)
            usersRepository.delete(userId)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when user does not exist`() = runTest {
        // Arrange
        coEvery { usersRepository.exists(userId) } returns false

        // Act
        val result = deleteUserUseCase(userId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerify(exactly = 1) { usersRepository.exists(userId) }
        coVerify(exactly = 0) { usersRepository.delete(any()) }
    }

    @Test
    fun `should fail with ModelDeleteException when delete returns false`() = runTest {
        // Arrange
        coEvery { usersRepository.exists(userId) } returns true
        coEvery { usersRepository.delete(userId) } returns false

        // Act
        val result = deleteUserUseCase(userId)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelDeleteException)

        coVerifySequence {
            usersRepository.exists(userId)
            usersRepository.delete(userId)
        }
    }
}
