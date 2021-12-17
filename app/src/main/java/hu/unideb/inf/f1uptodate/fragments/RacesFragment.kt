package hu.unideb.inf.f1uptodate.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import hu.unideb.inf.f1uptodate.R
import hu.unideb.inf.f1uptodate.databinding.FragmentRaceBinding
import hu.unideb.inf.f1uptodate.model.RaceResult
import hu.unideb.inf.f1uptodate.repository.Repository
import hu.unideb.inf.f1uptodate.view.RacesViewModel
import hu.unideb.inf.f1uptodate.view.RacesViewModelFactory

class RacesFragment : Fragment(){

    private lateinit var viewModel: RacesViewModel
    private var results : MutableList<RaceResult> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val binding: FragmentRaceBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_race, container, false
        )

        binding.apply{
            btnGetRaces.setOnClickListener {
                val year = Integer.parseInt(editTextYear.text.toString())
               getRaces(year)
                //TODO: List races
            }
        }

        return binding.root
    }

    private fun getRaces(year : Int) {
        val repository = Repository()
        val viewModelFactory = RacesViewModelFactory(repository)
        viewModel= ViewModelProvider(this,viewModelFactory)[RacesViewModel::class.java]
        viewModel.getRace(year)
        viewModel.myResponse.observe(viewLifecycleOwner,{ response ->
            if(response.isSuccessful) {
                val raceTable = response.body()?.mrData?.raceTable
                for(race in raceTable?.races!!) {
                    val result = race.results[0]
                    val name = result.driver.givenName + " " + result.driver.familyName
                    results.add(RaceResult(race.round,race.raceName,name,race.date))
                }

            } else {
                Log.d("Response",response.errorBody().toString())
            }

        })

    }
}