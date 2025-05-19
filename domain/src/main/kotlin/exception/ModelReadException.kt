package exception

import java.util.*

class ModelReadException(modelName: String, modelId: UUID) :
    RuntimeException("Failed to read $modelName with id $modelId")
