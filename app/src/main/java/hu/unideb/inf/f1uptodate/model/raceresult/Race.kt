package hu.unideb.inf.f1uptodate.model.raceresult

import com.google.gson.annotations.SerializedName

data class Race (
    @SerializedName("round")
    val round:String,
    @SerializedName("raceName")
    val raceName:String,
    @SerializedName("date")
    val date:String,
    @SerializedName("Results")
    val results: List<Result>
        )