package hu.unideb.inf.f1uptodate.model

import com.google.gson.annotations.SerializedName

data class ResponseData(
    @SerializedName("MRData")
    val mrData: MRData
)
