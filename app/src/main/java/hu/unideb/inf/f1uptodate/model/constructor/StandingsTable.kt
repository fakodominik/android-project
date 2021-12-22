package hu.unideb.inf.f1uptodate.model.constructor

import com.google.gson.annotations.SerializedName

data class StandingsTable(
    @SerializedName("StandingsLists")
    val standingsList: List<StandingsList>
)
