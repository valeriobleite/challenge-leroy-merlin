package pt.leite.valerio.domain.repositories

import pt.leite.valerio.domain.entities.TaskEntity

interface TaskRepository {
    suspend fun getTasks(): List<TaskEntity>
    suspend fun insertTask(taskEntity: TaskEntity)
    suspend fun deleteTask(id: Int)
}