package code.yakovenko.tariffka.integration.exposed.repository

import code.yakovenko.tariffka.data.mapping.operator.OperatorEntityToDomainMapper
import code.yakovenko.tariffka.data.mapping.operator.OperatorToEntityMapper
import code.yakovenko.tariffka.data.repository.DatabaseOperatorRepository
import code.yakovenko.tariffka.integration.exposed.TestExposedDatabaseController
import code.yakovenko.tariffka.testOperator
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class OperatorRepositoryExposedIntegrationTest {
    private val operatorRepository = DatabaseOperatorRepository(
        OperatorToEntityMapper(),
        OperatorEntityToDomainMapper()
    )

    @BeforeTest
    fun setUp() {
        TestExposedDatabaseController.setUp()
    }

    @AfterTest
    fun cleanUp() {
        TestExposedDatabaseController.cleanUp()
    }

    @Test
    fun `should create and retrieve operator`() {
        val createOperator = operatorRepository.create(testOperator)

        val readOperator = operatorRepository.readById(testOperator.id)

        assertNotNull(readOperator)
        assertEquals(createOperator, testOperator)
        assertEquals(readOperator, testOperator)
    }

    @Test
    fun `should update and retrieve operator`() {
        operatorRepository.create(testOperator)
        val updateTestOperator = testOperator.copy(url = "https://test.com")
        val updateOperator = operatorRepository.update(updateTestOperator)

        val readOperator = operatorRepository.readById(updateTestOperator.id)

        assertNotNull(readOperator)
        assertEquals(updateOperator, updateTestOperator)
        assertEquals(readOperator, updateTestOperator)
    }

    @Test
    fun `should delete operator`() {
        operatorRepository.create(testOperator)
        operatorRepository.deleteById(testOperator.id)

        val operator = operatorRepository.readById(testOperator.id)

        assertNull(operator)
    }
}
