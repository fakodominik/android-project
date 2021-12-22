package hu.unideb.inf.f1uptodate.repository

import hu.unideb.inf.f1uptodate.api.RetrofitInstance
import hu.unideb.inf.f1uptodate.model.championship.ResponseDataChamp
import hu.unideb.inf.f1uptodate.model.constructor.ResponseDataConst
import hu.unideb.inf.f1uptodate.model.raceresult.ResponseData
import retrofit2.Response

class Repository {

    suspend fun getRace(year : Int): Response<ResponseData> {
        return RetrofitInstance.api.getRaces(year)
    }

    suspend fun getChampionship(year : Int): Response<ResponseDataChamp> {
        return RetrofitInstance.api.getChampionship(year)
    }

    suspend fun getConstructors(year : Int): Response<ResponseDataConst> {
        return RetrofitInstance.api.getConstructor(year)
    }

}