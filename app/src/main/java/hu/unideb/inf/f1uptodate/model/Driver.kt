package hu.unideb.inf.f1uptodate.model

import com.google.gson.annotations.SerializedName

data class Driver(
    @SerializedName("givenName")
    val givenName:String,
    @SerializedName("familyName")
    val familyName:String,
)
