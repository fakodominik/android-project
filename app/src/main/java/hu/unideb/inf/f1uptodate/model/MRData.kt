package hu.unideb.inf.f1uptodate.model

import com.google.gson.annotations.SerializedName

data class MRData (
    @SerializedName("RaceTable")
    val raceTable: RaceTable
)