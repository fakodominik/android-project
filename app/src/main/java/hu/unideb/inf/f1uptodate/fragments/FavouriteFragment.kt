package hu.unideb.inf.f1uptodate.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import hu.unideb.inf.f1uptodate.R
import hu.unideb.inf.f1uptodate.adapter.RaceAdapter
import hu.unideb.inf.f1uptodate.database.FavouriteYearDatabase
import hu.unideb.inf.f1uptodate.database.model.Year
import hu.unideb.inf.f1uptodate.databinding.FavouriteFragmentBinding
import hu.unideb.inf.f1uptodate.fragments.views.FavouriteViewModel
import hu.unideb.inf.f1uptodate.fragments.views.FavouriteViewModelFactory
import hu.unideb.inf.f1uptodate.model.RaceResult
import hu.unideb.inf.f1uptodate.repository.Repository

class FavouriteFragment : Fragment() {

    private lateinit var binding: FavouriteFragmentBinding
    private lateinit var viewModel: FavouriteViewModel
    private lateinit var favYearsInt: ArrayList<Int>
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: RaceAdapter
    private var raceResults : MutableList<RaceResult> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.favourite_fragment, container, false
        )

        setupViewModel()

        binding.apply {
            rvList.setHasFixedSize(true)
            linearLayoutManager = LinearLayoutManager(activity)
            rvList.layoutManager = linearLayoutManager
            btnRaces.setOnClickListener {
                if(spinnerFavYears.selectedItemPosition > -1) {
                    val year = Integer.valueOf(spinnerFavYears.selectedItem.toString())
                    getRaces(year)
                    setRvContent()
                }
            }
            btnDeleteFavs.setOnClickListener{
                deleteYears()
            }
        }

        return binding.root
    }

    private fun setRvContent() {
        adapter = RaceAdapter(activity?.baseContext!!, raceResults)
        binding.rvList.adapter = adapter
    }

    private fun deleteYears() {
        viewModel.deleteYears()
        setSpinnerContent(binding.spinnerFavYears)
        raceResults = ArrayList()
        setRvContent()
    }

    override fun onStart() {
        super.onStart()
        setSpinnerContent(binding.spinnerFavYears)
    }

    private fun setSpinnerContent(spinnerFavYears: Spinner) {
        viewModel.getFavYears()
        viewModel.favYearList.observe(viewLifecycleOwner, {
            val favYears = it
            favYearsInt = ArrayList()
            for(year in favYears) {
                favYearsInt.add(year.year)
            }
            val adapter: ArrayAdapter<Int> = ArrayAdapter<Int>(
                activity?.baseContext !!,
                android.R.layout.simple_spinner_item, favYearsInt
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerFavYears.adapter = adapter
        })
    }

    private fun setupViewModel() {
        val repository = Repository()
        val application = requireNotNull(this.activity).application
        val dbDao = FavouriteYearDatabase.getInstance(application).favouriteYearDatabaseDao
        val viewModelFactory = FavouriteViewModelFactory(dbDao,repository)
        viewModel= ViewModelProvider(this,viewModelFactory)[FavouriteViewModel::class.java]
    }

    private fun getRaces(year : Int) {
        viewModel.getRace(year)
        viewModel.myResponse.observe(viewLifecycleOwner,{ response ->
            if(response.isSuccessful) {
                val raceTable = response.body()?.mrData?.raceTable
                raceResults.clear()
                for(race in raceTable?.races!!) {
                    val result = race.results[0]
                    val name = result.driver.givenName + " " + result.driver.familyName
                    raceResults.add(RaceResult(race.round,race.raceName,name,race.date))
                }

            } else {
                Log.d("Response", response.errorBody().toString())
            }

        })

    }

}