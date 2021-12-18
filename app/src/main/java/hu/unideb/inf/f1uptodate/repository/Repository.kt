package hu.unideb.inf.f1uptodate.repository

import hu.unideb.inf.f1uptodate.api.RetrofitInstance
import hu.unideb.inf.f1uptodate.model.MRData
import hu.unideb.inf.f1uptodate.model.ResponseData
import retrofit2.Response

class Repository {

    suspend fun getRace(year : Int): Response<ResponseData> {
        return RetrofitInstance.api.get(year)
    }

}