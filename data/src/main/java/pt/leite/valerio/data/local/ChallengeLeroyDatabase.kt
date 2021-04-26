package pt.leite.valerio.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import pt.leite.valerio.data.local.dao.TaskDAO
import pt.leite.valerio.data.local.entities.TaskRoom

@Database(
    entities = [TaskRoom::class],
    version = 1
)
abstract class ChallengeLeroyDatabase: RoomDatabase() {
    abstract val taskDAO: TaskDAO

    companion object {
        fun getInstance(context: Context) = Room.databaseBuilder(context, ChallengeLeroyDatabase::class.java, "challenge-leroy")
            .addCallback(Callback())
            .build()
    }

    class Callback: RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            db.execSQL("INSERT INTO task VALUES (NULL, 'Tarefa 1', 'Descrição modelo 1', 1)")
            db.execSQL("INSERT INTO task VALUES (NULL, 'Tarefa 2', 'Descrição modelo 2', 1)")
        }
    }
}