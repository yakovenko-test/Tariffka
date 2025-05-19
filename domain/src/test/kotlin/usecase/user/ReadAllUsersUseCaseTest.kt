package usecase.user

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import model.TestUser
import model.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import repository.UsersRepository

class ReadAllUsersUseCaseTest {
    private lateinit var readAllUsersUseCase: ReadAllUsersUseCase

    private val usersRepository: UsersRepository = mockk()

    @BeforeEach
    fun setUp() {
        readAllUsersUseCase = ReadAllUsersUseCase(usersRepository)
    }

    @Test
    fun `should read all users successfully`() = runTest {
        // Arrange
        val users = listOf(
            TestUser.createRegularUser(),
            TestUser.createRegularUser()
        )

        coEvery { usersRepository.readAll() } returns users

        // Act
        val result = readAllUsersUseCase()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(users, result.getOrNull())

        coVerify(exactly = 1) { usersRepository.readAll() }
    }

    @Test
    fun `should return empty collection when no users exist`() = runTest {
        // Arrange
        coEvery { usersRepository.readAll() } returns emptyList<User>()

        // Act
        val result = readAllUsersUseCase()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(emptyList<User>(), result.getOrNull())

        coVerify(exactly = 1) { usersRepository.readAll() }
    }
}