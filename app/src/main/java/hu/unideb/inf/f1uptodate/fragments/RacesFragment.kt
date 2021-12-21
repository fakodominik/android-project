package hu.unideb.inf.f1uptodate.fragments

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import hu.unideb.inf.f1uptodate.R
import hu.unideb.inf.f1uptodate.adapter.RaceAdapter
import hu.unideb.inf.f1uptodate.databinding.FragmentRaceBinding
import hu.unideb.inf.f1uptodate.model.RaceResult
import hu.unideb.inf.f1uptodate.repository.Repository
import hu.unideb.inf.f1uptodate.fragments.views.RacesViewModel
import hu.unideb.inf.f1uptodate.fragments.views.RacesViewModelFactory
import java.util.*
import kotlin.collections.ArrayList

import android.widget.ArrayAdapter
import android.widget.Toast
import hu.unideb.inf.f1uptodate.database.FavouriteYearDatabase

class RacesFragment : Fragment(){

    private lateinit var viewModel: RacesViewModel
    private lateinit var adapter: RaceAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var results : MutableList<RaceResult> = ArrayList()
    private var years : MutableList<Int> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val binding: FragmentRaceBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_race, container, false
        )

        setupViewModel()

        binding.apply{
            setSpinnerContent(spinnerYears)
            rvListOfRaces.setHasFixedSize(true)
            linearLayoutManager = LinearLayoutManager(activity)
            rvListOfRaces.layoutManager = linearLayoutManager
            btnGetRaces.setOnClickListener {
                val year = Integer.valueOf(spinnerYears.selectedItem.toString())
                getRaces(year)
                adapter = RaceAdapter(activity?.baseContext !!,results)
                rvListOfRaces.adapter = adapter
            }
            btnSetFavouriteYear.setOnClickListener{
                val year = Integer.valueOf(spinnerYears.selectedItem.toString())
                addToFavouriteDb(year)
            }
        }

        return binding.root
    }

    private fun setupViewModel() {
        val repository = Repository()
        val application = requireNotNull(this.activity).application
        val dbDao = FavouriteYearDatabase.getInstance(application).favouriteYearDatabaseDao
        val viewModelFactory = RacesViewModelFactory(repository,dbDao)
        viewModel= ViewModelProvider(this,viewModelFactory)[RacesViewModel::class.java]
    }

    private fun addToFavouriteDb(year: Int) {
        viewModel.setFavouriteYear(year)
        Toast.makeText(context, "Added $year to favourite years!",Toast.LENGTH_SHORT).show()
    }

    private fun setSpinnerContent(spinnerYears: Spinner) {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val startYear = 1950

        for(i in startYear..currentYear) {
            years.add(i)
        }

        val adapter: ArrayAdapter<Int> = ArrayAdapter<Int>(
            activity?.baseContext !!,
            android.R.layout.simple_spinner_item, years
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerYears.adapter = adapter

    }

    private fun getRaces(year : Int) {
        viewModel.getRace(year)
        viewModel.myResponse.observe(viewLifecycleOwner,{ response ->
            if(response.isSuccessful) {
                val raceTable = response.body()?.mrData?.raceTable
                results.clear()
                for(race in raceTable?.races!!) {
                    val result = race.results[0]
                    val name = result.driver.givenName + " " + result.driver.familyName
                    results.add(RaceResult(race.round,race.raceName,name,race.date))
                }

            } else {
                d("Response",response.errorBody().toString())
            }

        })

    }
}