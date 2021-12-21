package hu.unideb.inf.f1uptodate.fragments.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hu.unideb.inf.f1uptodate.database.FavouriteYearDatabaseDao
import hu.unideb.inf.f1uptodate.repository.Repository

class FavouriteViewModelFactory(private val dbDao: FavouriteYearDatabaseDao, private val repository: Repository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavouriteViewModel(dbDao,repository) as T
    }


}