package ke.co.topup.todo.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task_table WHERE name LIKE '%' || :searchQuery || '%' ORDER BY important DESC")
    fun  getTasks(searchQuery: String): Flow<List<Task>>

    @Insert
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)
}
