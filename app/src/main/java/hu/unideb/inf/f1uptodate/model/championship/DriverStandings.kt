package hu.unideb.inf.f1uptodate.model.championship

import com.google.gson.annotations.SerializedName
import hu.unideb.inf.f1uptodate.model.Driver

data class DriverStandings (
    @SerializedName("position")
    val position: String,
    @SerializedName("points")
    val points: String,
    @SerializedName("Driver")
    val driver: Driver,
    @SerializedName("Constructors")
    val constructors: List<Constructors>
        )