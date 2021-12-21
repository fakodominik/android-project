package hu.unideb.inf.f1uptodate.api

import hu.unideb.inf.f1uptodate.model.championship.ResponseDataChamp
import hu.unideb.inf.f1uptodate.model.raceresult.ResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface OnlineF1API {

    @GET("api/f1/{year}/results/1.json")
    suspend fun getRaces(
        @Path("year") year: Int
    ): Response<ResponseData>

    @GET("api/f1/{year}/driverStandings.json")
    suspend fun getChampionship(
        @Path("year") year: Int
    ): Response<ResponseDataChamp>


}