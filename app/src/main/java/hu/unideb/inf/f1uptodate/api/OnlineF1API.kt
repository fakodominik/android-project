package hu.unideb.inf.f1uptodate.api

import hu.unideb.inf.f1uptodate.model.MRData
import hu.unideb.inf.f1uptodate.model.ResponseData
import retrofit2.http.GET

interface OnlineF1API {

    @GET("api/f1/2021/results/1.json")
    suspend fun get(): ResponseData

}