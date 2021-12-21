package hu.unideb.inf.f1uptodate.model.championship

import com.google.gson.annotations.SerializedName

data class StandingsList (
    @SerializedName("DriverStandings")
    val driverStandingsList: List<DriverStandings>
        )