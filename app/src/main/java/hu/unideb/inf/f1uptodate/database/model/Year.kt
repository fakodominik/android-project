package hu.unideb.inf.f1uptodate.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_years_table", indices = [Index(value = ["year"], unique = true)])
data class Year(
    @ColumnInfo(name="year")
    val year : Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Year

        if (year != other.year) return false

        return true
    }

    override fun hashCode(): Int {
        return year
    }
}
