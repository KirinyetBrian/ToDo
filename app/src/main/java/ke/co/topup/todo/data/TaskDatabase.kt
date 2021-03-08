package ke.co.topup.todo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import ke.co.topup.todo.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class],version = 1)
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
                dao.insert(Task(0,"Wash the dishes",completed = true))
                dao.insert(Task(1,"Study for exams",important = true))
                dao.insert(Task(2,"Do homework",important = true))
                dao.insert(Task(4,"Visit a friend"))
                dao.insert(Task(5,"Cook supper"))
                dao.insert(Task(6,"Pray"))
                dao.insert(Task(7,"Latenight study",important = true))
            }
        }
    }
}