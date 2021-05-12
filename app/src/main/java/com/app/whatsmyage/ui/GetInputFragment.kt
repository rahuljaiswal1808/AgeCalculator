package com.app.whatsmyage.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.app.whatsmyage.R
import com.app.whatsmyage.viewmodel.MainActivityViewModel
import java.util.*

class GetInputFragment : Fragment() {
    private lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_get_input, container, false)
        val datePicker = view.findViewById<DatePicker>(R.id.cvDob)
        datePicker.maxDate = Calendar.getInstance().timeInMillis
        viewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
        val firstName = view.findViewById<EditText>(R.id.etFirstName)
        val lastName = view.findViewById<EditText>(R.id.etLastName)
        val btnGetAge = view.findViewById<Button>(R.id.btnGetAge)
        btnGetAge.setOnClickListener {
            viewModel.getAge(datePicker.year, datePicker.month, datePicker.dayOfMonth, firstName.text.toString(), lastName.text.toString())
            view.findNavController().navigate(R.id.action_getInputFragment_to_displayAgeFragment)
        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GetInputFragment().apply {}
    }
}