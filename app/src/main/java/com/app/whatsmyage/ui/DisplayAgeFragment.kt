package com.app.whatsmyage.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.app.whatsmyage.R
import com.app.whatsmyage.viewmodel.MainActivityViewModel

class DisplayAgeFragment : Fragment() {

    private lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_display_age, container, false)
        val tvAge = view.findViewById<TextView>(R.id.tvAge)
        tvAge.text = viewModel.currentAge
        val btnCalculateOther = view.findViewById<Button>(R.id.btnCalculateOther)
        btnCalculateOther.setOnClickListener {
            view.findNavController().popBackStack()
        }
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DisplayAgeFragment().apply {}
    }
}