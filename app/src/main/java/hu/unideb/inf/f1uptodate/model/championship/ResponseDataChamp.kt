package hu.unideb.inf.f1uptodate.model.championship

import com.google.gson.annotations.SerializedName

data class ResponseDataChamp (
    @SerializedName("MRData")
    val mrData: MRData
        )