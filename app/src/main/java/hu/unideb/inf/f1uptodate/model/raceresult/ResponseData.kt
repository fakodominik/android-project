package hu.unideb.inf.f1uptodate.model.raceresult

import com.google.gson.annotations.SerializedName

data class ResponseData(
    @SerializedName("MRData")
    val mrData: MRData
)
