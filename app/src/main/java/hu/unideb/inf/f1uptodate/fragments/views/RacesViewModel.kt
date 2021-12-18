package hu.unideb.inf.f1uptodate.fragments.views

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.unideb.inf.f1uptodate.model.ResponseData
import hu.unideb.inf.f1uptodate.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class RacesViewModel(private val repository: Repository):ViewModel() {

    val myResponse: MutableLiveData<Response<ResponseData>> = MutableLiveData()

    fun getRace(year : Int) {
        viewModelScope.launch {
            val response = repository.getRace(year)
            myResponse.value = response
        }
    }
}