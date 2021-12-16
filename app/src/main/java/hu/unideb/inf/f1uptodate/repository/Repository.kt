package hu.unideb.inf.f1uptodate.repository

import hu.unideb.inf.f1uptodate.api.RetrofitInstance
import hu.unideb.inf.f1uptodate.model.MRData
import hu.unideb.inf.f1uptodate.model.ResponseData

class Repository {

    suspend fun getRace(): ResponseData {
        return RetrofitInstance.api.get()
    }

}