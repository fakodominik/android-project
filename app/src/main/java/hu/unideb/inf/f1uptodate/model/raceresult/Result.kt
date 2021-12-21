package hu.unideb.inf.f1uptodate.model.raceresult

import com.google.gson.annotations.SerializedName
import hu.unideb.inf.f1uptodate.model.Driver

data class Result(
    @SerializedName("number")
    val number:String,
    @SerializedName("position")
    val position:String,
    @SerializedName("Driver")
    val driver: Driver,
    @SerializedName("Constructor")
    val constructor: Constructor
)
