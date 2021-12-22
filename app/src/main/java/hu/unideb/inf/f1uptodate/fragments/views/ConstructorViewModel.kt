package hu.unideb.inf.f1uptodate.fragments.views

import androidx.lifecycle.*
import hu.unideb.inf.f1uptodate.database.FavouriteYearDatabaseDao
import hu.unideb.inf.f1uptodate.database.model.Year
import hu.unideb.inf.f1uptodate.model.constructor.ResponseDataConst
import hu.unideb.inf.f1uptodate.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class ConstructorViewModel(private val repository: Repository, private val dbDao: FavouriteYearDatabaseDao):ViewModel() {

    val myResponse: MutableLiveData<Response<ResponseDataConst>> = MutableLiveData()

    fun getResult(year : Int) {
        viewModelScope.launch {
            myResponse.value = repository.getConstructors(year)
        }
    }

    fun setFavouriteYear(year : Int) {
        viewModelScope.launch {
            setFavouriteYearToAccessDao(year)
        }
    }

    private suspend fun setFavouriteYearToAccessDao(year: Int) {
        dbDao.insert(Year(year))
    }
}