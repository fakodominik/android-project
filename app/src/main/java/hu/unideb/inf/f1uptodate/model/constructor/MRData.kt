package hu.unideb.inf.f1uptodate.model.constructor

import com.google.gson.annotations.SerializedName

data class MRData (
    @SerializedName("StandingsTable")
    val standingsTable: StandingsTable

)