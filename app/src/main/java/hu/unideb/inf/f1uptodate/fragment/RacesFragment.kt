package hu.unideb.inf.f1uptodate.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hu.unideb.inf.f1uptodate.R
import hu.unideb.inf.f1uptodate.databinding.FragmentRaceBinding
import hu.unideb.inf.f1uptodate.repository.Repository
import hu.unideb.inf.f1uptodate.view.RacesViewModel
import hu.unideb.inf.f1uptodate.view.RacesViewModelFactory

class RacesFragment : Fragment(){

    private lateinit var viewModel: RacesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentRaceBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_race, container, false
        )

        binding.apply{
            btnGetRaces.setOnClickListener {
               getRaces()
            }
        }

        return binding.root
    }

    private fun getRaces() {
        val repository = Repository()
        val viewModelFactory = RacesViewModelFactory(repository)
        viewModel= ViewModelProvider(this,viewModelFactory)[RacesViewModel::class.java]
        viewModel.getRace()
        viewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
            Log.d("Response", response.mrData.raceTable.season)
            Log.d("Response", response.mrData.raceTable.races[0].round)
            Log.d("Response", response.mrData.raceTable.races[0].raceName)
            Log.d("Response", response.mrData.raceTable.races[0].date)
            Log.d("Response", response.mrData.raceTable.races[0].results[0].number)
            Log.d("Response", response.mrData.raceTable.races[0].results[0].position)
            Log.d("Response", response.mrData.raceTable.races[0].results[0].driver.code)
            Log.d("Response", response.mrData.raceTable.races[0].results[0].driver.givenName +
                    response.mrData.raceTable.races[0].results[0].driver.familyName)
            Log.d("Response", response.mrData.raceTable.races[0].results[0].constructor.name)
        })

    }
}