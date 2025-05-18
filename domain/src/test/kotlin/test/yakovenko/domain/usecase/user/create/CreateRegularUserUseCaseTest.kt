package test.yakovenko.domain.usecase.user.create

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
import test.yakovenko.domain.model.TestUser
import test.yakovenko.domain.repository.OperatorsRepository
import test.yakovenko.domain.repository.ServicesRepository
import test.yakovenko.domain.repository.TariffsRepository
import test.yakovenko.domain.repository.UsersRepository
import java.util.UUID

class CreateRegularUserUseCaseTest {
    private lateinit var createRegularUserUseCase: CreateRegularUserUseCase

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
        createRegularUserUseCase = CreateRegularUserUseCase(
            usersRepository,
            operatorsRepository,
            servicesRepository,
            tariffsRepository
        )
    }

    @Test
    fun `should create regular user successfully`() = runTest {
        // Arrange
        coEvery { usersRepository.exists(regularUser.id) } returns false
        regularUser.operatorId?.let {
            coEvery { operatorsRepository.exists(regularUser.operatorId) } returns true
        }
        regularUser.tariffId?.let {
            coEvery { tariffsRepository.exists(regularUser.tariffId) } returns true
        }
        regularUser.serviceIds.forEach { coEvery { servicesRepository.exists(it) } returns true }
        coEvery { usersRepository.create(regularUser) } returns regularUser

        // Act
        val result = createRegularUserUseCase(regularUser)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(regularUser, result.getOrNull())

        coVerifySequence {
            usersRepository.exists(regularUser.id)
            regularUser.operatorId?.let { operatorsRepository.exists(regularUser.operatorId) }
            regularUser.tariffId?.let { tariffsRepository.exists(regularUser.tariffId) }
            regularUser.serviceIds.forEach { servicesRepository.exists(it) }
            usersRepository.create(regularUser)
        }
    }

    @Test
    fun `should fail with ModelDuplicateException when regular user already exists`() = runTest {
        // Arrange
        coEvery { usersRepository.exists(regularUser.id) } returns true

        // Act
        val result = createRegularUserUseCase(regularUser)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelDuplicateException)

        coVerify(exactly = 1) { usersRepository.exists(regularUser.id) }
        coVerify(exactly = 0) { operatorsRepository.exists(any()) }
        coVerify(exactly = 0) { tariffsRepository.exists(any()) }
        coVerify(exactly = 0) { servicesRepository.exists(any()) }
        coVerify(exactly = 0) { usersRepository.create(any()) }
    }

    @Test
    fun `should fail with ModelNotFoundException when operator does not exist`() = runTest {
        assumeTrue(regularUser.operatorId != null)

        // Arrange
        coEvery { usersRepository.exists(regularUser.id) } returns false
        coEvery { operatorsRepository.exists(regularUser.operatorId!!) } returns false

        // Act
        val result = createRegularUserUseCase(regularUser)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerifySequence {
            usersRepository.exists(regularUser.id)
            operatorsRepository.exists(regularUser.operatorId!!)
        }
        coVerify(exactly = 0) { tariffsRepository.exists(any()) }
        coVerify(exactly = 0) { servicesRepository.exists(any()) }
        coVerify(exactly = 0) { usersRepository.create(any()) }
    }

    @Test
    fun `should fail with ModelNotFoundException when tariff does not exist`() = runTest {
        assumeTrue(regularUser.tariffId != null)

        // Arrange
        coEvery { usersRepository.exists(regularUser.id) } returns false
        regularUser.operatorId?.let {
            coEvery { operatorsRepository.exists(regularUser.operatorId) } returns true
        }
        coEvery { tariffsRepository.exists(regularUser.tariffId!!) } returns false

        // Act
        val result = createRegularUserUseCase(regularUser)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelNotFoundException)

        coVerifySequence {
            usersRepository.exists(regularUser.id)
            regularUser.operatorId?.let { operatorsRepository.exists(regularUser.operatorId) }
            tariffsRepository.exists(regularUser.tariffId!!)
        }
        coVerify(exactly = 0) { servicesRepository.exists(any()) }
        coVerify(exactly = 0) { usersRepository.create(any()) }
    }

    @Test
    fun `should fail with ModelNotFoundException when one of the services does not exist`() =
        runTest {
            // Arrange
            coEvery { usersRepository.exists(regularUser.id) } returns false
            regularUser.operatorId?.let {
                coEvery { operatorsRepository.exists(regularUser.operatorId) } returns true
            }
            regularUser.tariffId?.let {
                coEvery { tariffsRepository.exists(regularUser.tariffId) } returns true
            }
            coEvery { servicesRepository.exists(regularUser.serviceIds.first()) } returns false

            // Act
            val result = createRegularUserUseCase(regularUser)

            // Assert
            assertTrue(result.isFailure)
            assertTrue(result.exceptionOrNull() is ModelNotFoundException)

            coVerifySequence {
                usersRepository.exists(regularUser.id)
                regularUser.operatorId?.let { operatorsRepository.exists(regularUser.operatorId) }
                regularUser.tariffId?.let { tariffsRepository.exists(regularUser.tariffId) }
                servicesRepository.exists(regularUser.serviceIds.first())
            }
            coVerify(exactly = 0) { usersRepository.create(any()) }
        }

    @Test
    fun `should fail with ModelCreateException when create returns null`() = runTest {
        // Arrange
        coEvery { usersRepository.exists(regularUser.id) } returns false
        regularUser.operatorId?.let {
            coEvery { operatorsRepository.exists(regularUser.operatorId) } returns true
        }
        regularUser.tariffId?.let {
            coEvery { tariffsRepository.exists(regularUser.tariffId) } returns true
        }
        regularUser.serviceIds.forEach { coEvery { servicesRepository.exists(it) } returns true }
        coEvery { usersRepository.create(regularUser) } returns null

        // Act
        val result = createRegularUserUseCase(regularUser)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ModelCreateException)

        coVerifySequence {
            usersRepository.exists(regularUser.id)
            regularUser.operatorId?.let { operatorsRepository.exists(regularUser.operatorId) }
            regularUser.tariffId?.let { tariffsRepository.exists(regularUser.tariffId) }
            regularUser.serviceIds.forEach { servicesRepository.exists(it) }
            usersRepository.create(regularUser)
        }
    }
}
