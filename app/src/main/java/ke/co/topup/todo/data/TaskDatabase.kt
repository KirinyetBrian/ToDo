package ke.co.topup.todo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import ke.co.topup.todo.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class],version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase(){

    abstract fun taskDao(): TaskDao

    class Callback @Inject constructor(
        private val database: Provider<TaskDatabase>,
       @ApplicationScope private val applicationScope: CoroutineScope
    ): RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().taskDao()

            applicationScope.launch {
                dao.insert(Task("Wash the dishes",completed = true))
                dao.insert(Task("Study for exams",important = true))
                dao.insert(Task("Do homework",important = true))
                dao.insert(Task("Visit a friend"))
                dao.insert(Task("Cook supper"))
                dao.insert(Task("Pray"))
                dao.insert(Task("Latenight study",important = true))
            }
        }
    }
}