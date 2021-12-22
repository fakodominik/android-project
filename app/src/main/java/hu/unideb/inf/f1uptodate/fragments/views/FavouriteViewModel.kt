package hu.unideb.inf.f1uptodate.fragments.views

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.unideb.inf.f1uptodate.database.FavouriteYearDatabaseDao
import hu.unideb.inf.f1uptodate.database.model.Year
import hu.unideb.inf.f1uptodate.model.championship.ResponseDataChamp
import hu.unideb.inf.f1uptodate.model.constructor.ResponseDataConst
import hu.unideb.inf.f1uptodate.model.raceresult.ResponseData
import hu.unideb.inf.f1uptodate.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class FavouriteViewModel(private val dbDao: FavouriteYearDatabaseDao, private val repository: Repository):
    ViewModel() {

    val myResponse: MutableLiveData<Response<ResponseData>> = MutableLiveData()
    val champResponse: MutableLiveData<Response<ResponseDataChamp>> = MutableLiveData()
    val constResponse: MutableLiveData<Response<ResponseDataConst>> = MutableLiveData()
    var favYearList: MutableLiveData<List<Year>> = MutableLiveData()

        fun getFavYears() {
            viewModelScope.launch {
                favYearList.value = getYears()
            }
        }

    private suspend fun getYears(): List<Year> {
        return dbDao.getYears()
    }

    fun getRace(year : Int){
        viewModelScope.launch {
            val response = repository.getRace(year)
            myResponse.value = response
        }
    }
    fun getResult(year : Int){
        viewModelScope.launch {
            val response = repository.getChampionship(year)
            champResponse.value = response
        }
    }
    fun getResultConst(year : Int){
        viewModelScope.launch {
            val response = repository.getConstructors(year)
            constResponse.value = response
        }
    }
    fun deleteYears(){
        viewModelScope.launch {
            deleteYearsDao()
        }
    }

    private suspend fun deleteYearsDao() {
        dbDao.clear()
    }

}