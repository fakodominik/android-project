package hu.unideb.inf.f1uptodate.model.raceresult

import com.google.gson.annotations.SerializedName
import hu.unideb.inf.f1uptodate.model.raceresult.RaceTable

data class MRData (
    @SerializedName("RaceTable")
    val raceTable: RaceTable
)