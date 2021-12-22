package hu.unideb.inf.f1uptodate.fragments.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hu.unideb.inf.f1uptodate.database.FavouriteYearDatabaseDao
import hu.unideb.inf.f1uptodate.repository.Repository

class ConstructorViewModelFactory(private val repository: Repository, private val dbDao: FavouriteYearDatabaseDao): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ConstructorViewModel(repository,dbDao) as T
    }


}