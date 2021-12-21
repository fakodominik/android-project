package hu.unideb.inf.f1uptodate.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hu.unideb.inf.f1uptodate.database.model.Year

@Database(entities = [Year::class], version = 3, exportSchema = false)
abstract class FavouriteYearDatabase : RoomDatabase() {

    abstract val favouriteYearDatabaseDao: FavouriteYearDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: FavouriteYearDatabase? = null

        fun getInstance(context: Context): FavouriteYearDatabase {

            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FavouriteYearDatabase::class.java,
                        "favourites_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}
