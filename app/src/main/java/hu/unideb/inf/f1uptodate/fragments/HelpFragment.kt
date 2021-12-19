package hu.unideb.inf.f1uptodate.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import hu.unideb.inf.f1uptodate.R
import hu.unideb.inf.f1uptodate.databinding.FragmentHelpBinding

class HelpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        val binding: FragmentHelpBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_help, container, false
        )

        return binding.root
    }

}