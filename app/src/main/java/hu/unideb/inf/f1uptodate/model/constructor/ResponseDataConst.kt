package hu.unideb.inf.f1uptodate.model.constructor

import com.google.gson.annotations.SerializedName

data class ResponseDataConst(
    @SerializedName("MRData")
    val mrData: MRData
)
