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
import hu.unideb.inf.f1uptodate.repository.Repository
import java.util.*
import kotlin.collections.ArrayList

import android.widget.ArrayAdapter
import android.widget.Toast
import hu.unideb.inf.f1uptodate.adapter.ChampionshipAdapter
import hu.unideb.inf.f1uptodate.database.FavouriteYearDatabase
import hu.unideb.inf.f1uptodate.databinding.FragmentChampionshipBinding
import hu.unideb.inf.f1uptodate.fragments.views.ChampionshipViewModel
import hu.unideb.inf.f1uptodate.fragments.views.ChampionshipViewModelFactory
import hu.unideb.inf.f1uptodate.model.championship.ChampionshipResult

class ChampionshipFragment : Fragment(){

    private lateinit var viewModel: ChampionshipViewModel
    private lateinit var adapter: ChampionshipAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var results : MutableList<ChampionshipResult> = ArrayList()
    private var years : MutableList<Int> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val binding: FragmentChampionshipBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_championship, container, false
        )

        setupViewModel()

        adapter = ChampionshipAdapter(activity?.baseContext !!, emptyList())

        binding.apply{
            rvListOfResult.adapter = adapter
            setSpinnerContent(spinnerYears)
            rvListOfResult.setHasFixedSize(true)
            linearLayoutManager = LinearLayoutManager(activity)
            rvListOfResult.layoutManager = linearLayoutManager
            btnGetResult.setOnClickListener {
                val year = Integer.valueOf(spinnerYears.selectedItem.toString())
                getResults(year)
                adapter.setData(results)
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
        val viewModelFactory = ChampionshipViewModelFactory(repository,dbDao)
        viewModel= ViewModelProvider(this,viewModelFactory)[ChampionshipViewModel::class.java]
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

    private fun getResults(year : Int) {
        viewModel.getResult(year)
        viewModel.myResponse.observe(viewLifecycleOwner,{ response ->
            if(response.isSuccessful) {
                val standingsList = response.body()?.mrData?.standingsTable?.standingsList?.get(0)
                results.clear()
                for(standings in standingsList?.driverStandingsList !!) {
                    val position = standings.position
                    val points = standings.points
                    val constructor = standings.constructors[0].name
                    val name = standings.driver.givenName + " " + standings.driver.familyName
                    results.add(ChampionshipResult(position,points,name,constructor))
                }

            } else {
                d("Response",response.errorBody().toString())
            }

        })
    }
}