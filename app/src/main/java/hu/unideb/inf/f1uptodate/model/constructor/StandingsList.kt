package hu.unideb.inf.f1uptodate.model.constructor

import com.google.gson.annotations.SerializedName

data class StandingsList(
    @SerializedName("ConstructorStandings")
    val constructorStandings: List<ConstructorStandings>
)
