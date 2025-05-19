package exception

import java.util.*

class ModelCreateException(modelName: String, modelId: UUID) :
    RuntimeException("Failed to create $modelName with id $modelId")
