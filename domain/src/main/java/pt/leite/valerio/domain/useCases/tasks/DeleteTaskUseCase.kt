package pt.leite.valerio.domain.useCases.tasks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.leite.valerio.domain.entities.TaskEntity
import pt.leite.valerio.domain.repositories.TaskRepository

class DeleteTaskUseCase(private val taskRepository: TaskRepository) {
    suspend fun call(id: Int) = withContext(Dispatchers.IO) {
        taskRepository.deleteTask(id)
    }
}