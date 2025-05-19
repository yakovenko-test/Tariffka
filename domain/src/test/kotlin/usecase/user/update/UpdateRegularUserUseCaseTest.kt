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
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import repository.OperatorsRepository
import repository.ServicesRepository
import repository.TariffsRepository
import repository.UsersRepository
import java.util.*

class UpdateRegularUserUseCaseTest {
    private lateinit var updateRegularUserUseCase: UpdateRegularUserUseCase

    private val usersRepository: UsersRepository = mockk()
    private val operatorsRepository: OperatorsRepository = mockk()
    private val servicesRepository: ServicesRepository = mockk()
    private val tariffsRepository: TariffsRepository = mockk()

    private val regularUser = TestUser.createRegularUser(
        operatorId = UUID.randomUUID(),
        tariffId = UUID.randomUUID(),
        serviceIds = listOf(UUID.randomUUID(), UUID.randomUUID())
    )

    @BeforeEach
    fun setUp() {
        updateRegularUserUseCase = UpdateRegularUserUseCase(
            usersRepository,
            operatorsRepository,
            servicesRepository,
            tariffsRepository
        )
    }

    @Test
    fun `should update regular user successfully`() = runTest {
        // Arrange
        coEvery { usersRepository.exists(regularUser.id) } returns true
        regularUser.operatorId?.let {
            coEvery { operatorsRepository.exists(regularUser.operatorId) } returns true
        }
        regularUser.tariffId?.let {
            coEvery { tariffsRepository.exists(regularUser.tariffId) } returns true
        }
        regularUser.serviceIds.forEach { coEvery { servicesRepository.exists(it) } returns true }
        coEvery { usersRepository.update(regularUser) } returns regularUser

        // Act
        val result = updateRegularUserUseCase(regularUser)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(regularUser, result.getOrNull())

        coVerifySequence {
            usersRepository.exists(regularUser.id)
            regularUser.operatorId?.let { operatorsRepository.exists(regularUser.operatorId) }
            regularUser.tariffId?.let { tariffsRepository.exists(regularUser.tariffId) }
            regularUser.serviceIds.forEach { servicesRepository.exists(it) }
            usersRepository.update(regularUser)
        }
    }

    @Test
    fun `should fail with ModelNotFoundException when regular user does not exist`() = runTest {
        // Arrange
        coEvery { usersRepository.exists(regularUser.id) } returns false

        // Act
        val result = updateRegularUserUseCase(regularUser)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerify(exactly = 1) { usersRepository.exists(regularUser.id) }
        coVerify(exactly = 0) { operatorsRepository.exists(any()) }
        coVerify(exactly = 0) { tariffsRepository.exists(any()) }
        coVerify(exactly = 0) { servicesRepository.exists(any()) }
        coVerify(exactly = 0) { usersRepository.update(any()) }
    }

    @Test
    fun `should fail with ModelNotFoundException when operator does not exist`() = runTest {
        assumeTrue(regularUser.operatorId != null)

        // Arrange
        coEvery { usersRepository.exists(regularUser.id) } returns true
        coEvery { operatorsRepository.exists(regularUser.operatorId!!) } returns false

        // Act
        val result = updateRegularUserUseCase(regularUser)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerifySequence {
            usersRepository.exists(regularUser.id)
            operatorsRepository.exists(regularUser.operatorId!!)
        }
        coVerify(exactly = 0) { tariffsRepository.exists(any()) }
        coVerify(exactly = 0) { servicesRepository.exists(any()) }
        coVerify(exactly = 0) { usersRepository.update(any()) }
    }

    @Test
    fun `should fail with ModelNotFoundException when tariff does not exist`() = runTest {
        assumeTrue(regularUser.tariffId != null)

        // Arrange
        coEvery { usersRepository.exists(regularUser.id) } returns true
        regularUser.operatorId?.let {
            coEvery { operatorsRepository.exists(regularUser.operatorId) } returns true
        }
        coEvery { tariffsRepository.exists(regularUser.tariffId!!) } returns false

        // Act
        val result = updateRegularUserUseCase(regularUser)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerifySequence {
            usersRepository.exists(regularUser.id)
            regularUser.operatorId?.let { operatorsRepository.exists(regularUser.operatorId) }
            tariffsRepository.exists(regularUser.tariffId!!)
        }
        coVerify(exactly = 0) { servicesRepository.exists(any()) }
        coVerify(exactly = 0) { usersRepository.update(any()) }
    }

    @Test
    fun `should fail with ModelNotFoundException when one of the services does not exist`() =
        runTest {
            // Arrange
            coEvery { usersRepository.exists(regularUser.id) } returns true
            regularUser.operatorId?.let {
                coEvery { operatorsRepository.exists(regularUser.operatorId) } returns true
            }
            regularUser.tariffId?.let {
                coEvery { tariffsRepository.exists(regularUser.tariffId) } returns true
            }
            coEvery { servicesRepository.exists(regularUser.serviceIds.first()) } returns false

            // Act
            val result = updateRegularUserUseCase(regularUser)

            // Assert
            assertTrue(result.isFailure)
            assertTrue(result.exceptionOrNull() is ModelNotFoundException)

            coVerifySequence {
                usersRepository.exists(regularUser.id)
                regularUser.operatorId?.let { operatorsRepository.exists(regularUser.operatorId) }
                regularUser.tariffId?.let { tariffsRepository.exists(regularUser.tariffId) }
                servicesRepository.exists(regularUser.serviceIds.first())
            }
            coVerify(exactly = 0) { usersRepository.update(any()) }
        }

    @Test
    fun `should fail with ModelUpdateException when update returns null`() = runTest {
        // Arrange
        coEvery { usersRepository.exists(regularUser.id) } returns true
        regularUser.operatorId?.let {
            coEvery { operatorsRepository.exists(regularUser.operatorId) } returns true
        }
        regularUser.tariffId?.let {
            coEvery { tariffsRepository.exists(regularUser.tariffId) } returns true
        }
        regularUser.serviceIds.forEach { coEvery { servicesRepository.exists(it) } returns true }
        coEvery { usersRepository.update(regularUser) } returns null

        // Act
        val result = updateRegularUserUseCase(regularUser)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelUpdateException)

        coVerifySequence {
            usersRepository.exists(regularUser.id)
            regularUser.operatorId?.let { operatorsRepository.exists(regularUser.operatorId) }
            regularUser.tariffId?.let { tariffsRepository.exists(regularUser.tariffId) }
            regularUser.serviceIds.forEach { servicesRepository.exists(it) }
            usersRepository.update(regularUser)
        }
    }
}
