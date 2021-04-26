package pt.leite.valerio.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pt.leite.valerio.data.local.entities.TaskRoom

@Dao
interface TaskDAO {
    @Query("SELECT * FROM task WHERE active = 1")
    suspend fun getAllActive(): List<TaskRoom>

    @Insert
    suspend fun insert(taskRoom: TaskRoom)

    @Query("UPDATE task SET active = 0 WHERE id = :id")
    suspend fun makeInactive(id: Int)
}