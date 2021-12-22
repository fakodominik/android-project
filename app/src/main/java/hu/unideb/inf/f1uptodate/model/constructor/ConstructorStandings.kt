package hu.unideb.inf.f1uptodate.model.constructor

import com.google.gson.annotations.SerializedName
import hu.unideb.inf.f1uptodate.model.Constructor

data class ConstructorStandings(
    @SerializedName("position")
    val position: String,
    @SerializedName("points")
    val points: String,
    @SerializedName("wins")
    val wins: String,
    @SerializedName("Constructor")
    val constructors: Constructor
)
