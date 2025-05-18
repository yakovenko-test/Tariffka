package test.yakovenko.domain.exception

import java.util.UUID

class ModelUpdateException(modelName: String, modelId: UUID) :
    RuntimeException("Failed to update $modelName with id $modelId")
