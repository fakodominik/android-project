package hu.unideb.inf.f1uptodate.model

import com.google.gson.annotations.SerializedName

data class RaceTable (
    @SerializedName("season")
    val season:String,
    @SerializedName("Races")
    val races:List<Race>
)