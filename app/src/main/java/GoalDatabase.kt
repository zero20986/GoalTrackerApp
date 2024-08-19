import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class GoalDatabase(context: Context) : SQLiteOpenHelper(context, "goals.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE goals (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT, is_completed INTEGER DEFAULT 0)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS goals")
        onCreate(db)
    }
}