package hu.unideb.inf.f1uptodate.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hu.unideb.inf.f1uptodate.database.model.Year

@Dao
interface FavouriteYearDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(year: Year)

    @Query("DELETE FROM favourite_years_table")
    suspend fun clear()

    @Query("SELECT * from favourite_years_table")
    suspend fun getYears(): List<Year>

}
