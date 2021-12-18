package hu.unideb.inf.f1uptodate.api

import hu.unideb.inf.f1uptodate.model.ResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface OnlineF1API {

    @GET("api/f1/{year}/results/1.json")
    suspend fun get(
        @Path("year") year: Int
    ): Response<ResponseData>

}