package test.yakovenko.domain.exception

import java.util.UUID

class ModelCreateException(modelName: String, modelId: UUID) :
    RuntimeException("Failed to create $modelName with id $modelId")
