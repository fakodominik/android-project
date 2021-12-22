package hu.unideb.inf.f1uptodate.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import hu.unideb.inf.f1uptodate.R
import hu.unideb.inf.f1uptodate.adapter.ChampionshipAdapter
import hu.unideb.inf.f1uptodate.adapter.ConstructorAdapter
import hu.unideb.inf.f1uptodate.adapter.RaceAdapter
import hu.unideb.inf.f1uptodate.database.FavouriteYearDatabase
import hu.unideb.inf.f1uptodate.database.model.Year
import hu.unideb.inf.f1uptodate.databinding.FavouriteFragmentBinding
import hu.unideb.inf.f1uptodate.fragments.views.FavouriteViewModel
import hu.unideb.inf.f1uptodate.fragments.views.FavouriteViewModelFactory
import hu.unideb.inf.f1uptodate.model.championship.ChampionshipResult
import hu.unideb.inf.f1uptodate.model.constructor.ConstructorResult
import hu.unideb.inf.f1uptodate.model.raceresult.RaceResult
import hu.unideb.inf.f1uptodate.repository.Repository

class FavouriteFragment : Fragment() {

    private lateinit var binding: FavouriteFragmentBinding
    private lateinit var viewModel: FavouriteViewModel
    private lateinit var favYearsInt: ArrayList<Int>
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var raceResults : MutableList<RaceResult> = ArrayList()
    private var championList : MutableList<ChampionshipResult> = ArrayList()
    private var constructorList : MutableList<ConstructorResult> = ArrayList()


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
                    val adapter = RaceAdapter(activity?.baseContext!!, raceResults)
                    rvList.adapter = adapter
                }
            }
            btnChampionships.setOnClickListener {
                if(spinnerFavYears.selectedItemPosition > -1) {
                    val year = Integer.valueOf(spinnerFavYears.selectedItem.toString())
                    getResults(year)
                    val adapter = ChampionshipAdapter(activity?.baseContext!!, championList)
                    rvList.adapter = adapter
                }
            }
            btnConstructors.setOnClickListener {
                if(spinnerFavYears.selectedItemPosition > -1) {
                    if((spinnerFavYears.selectedItem as Int) < 1958) {
                        Toast.makeText(context,"The first Constructors' Championship was awarded in 1958." +
                                " Select year from that year!",
                            Toast.LENGTH_LONG).show()
                    }else {
                        val year = Integer.valueOf(spinnerFavYears.selectedItem.toString())
                        getConstructorResults(year)
                        val adapter = ConstructorAdapter(activity?.baseContext!!, constructorList)
                        rvList.adapter = adapter
                    }
                }
            }
            btnDeleteFavs.setOnClickListener{
                deleteYears()
            }
        }

        return binding.root
    }

    private fun deleteYears() {
        viewModel.deleteYears()
        setSpinnerContent(binding.spinnerFavYears)
        raceResults = ArrayList()
        binding.rvList.adapter = null
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
    private fun getResults(year : Int) {
        viewModel.getResult(year)
        viewModel.champResponse.observe(viewLifecycleOwner,{ response ->
            if(response.isSuccessful) {
                val standingsList = response.body()?.mrData?.standingsTable?.standingsList?.get(0)
                championList.clear()
                for(standings in standingsList?.driverStandingsList !!) {
                    val position = standings.position
                    val points = standings.points
                    val constructor = standings.constructors[0].name
                    val name = standings.driver.givenName + " " + standings.driver.familyName
                    championList.add(ChampionshipResult(position,points,name,constructor))
                }

            } else {
                Log.d("Response", response.errorBody().toString())
            }

        })
    }
    private fun getConstructorResults(year : Int) {
        viewModel.getResultConst(year)
        viewModel.constResponse.observe(viewLifecycleOwner,{ response ->
            if(response.isSuccessful) {
                val standingsList = response.body()?.mrData?.standingsTable?.standingsList?.get(0)
                constructorList.clear()
                for(standings in standingsList?.constructorStandings !!) {
                    val position = standings.position
                    val points = standings.points
                    val wins = standings.wins
                    val name = standings.constructors.name
                    constructorList.add(ConstructorResult(position,points,name,wins))
                }

            } else {
                Log.d("Response", response.errorBody().toString())
            }

        })
    }

}