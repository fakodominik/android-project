package hu.unideb.inf.f1uptodate.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.unideb.inf.f1uptodate.model.MRData
import hu.unideb.inf.f1uptodate.model.ResponseData
import hu.unideb.inf.f1uptodate.repository.Repository
import kotlinx.coroutines.launch

class RacesViewModel(private val repository: Repository):ViewModel() {

    val myResponse: MutableLiveData<ResponseData> = MutableLiveData()

    fun getRace() {
        viewModelScope.launch {
            val response = repository.getRace()
            myResponse.value = response
        }
    }
}