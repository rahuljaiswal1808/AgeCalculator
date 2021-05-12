package com.app.whatsmyage.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import java.util.*

class MainActivityViewModel : ViewModel() {

    var currentAge = ""
    init {
        Log.i(MainActivityViewModel::class.simpleName, "MainActivityViewModel created!")
        currentAge = ""
    }
    fun getAge(year: Int, month: Int, day: Int, firstName: String, lastName: String){

        val dob: Calendar = Calendar.getInstance()
        val today: Calendar = Calendar.getInstance()
        dob.set(year, month, day)

        //Get difference between years
        var years = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)

        val currMonth: Int = today.get(Calendar.MONTH) + 1
        val birthMonth: Int = dob.get(Calendar.MONTH) + 1
        //Get difference between months
        var months = currMonth - birthMonth

        //if month difference is in negative then reduce years by one
        //and calculate the number of months.
        if (months < 0) {
            years--
            months = 12 - birthMonth + currMonth
            if (today.get(Calendar.DATE) < dob.get(Calendar.DATE)) months--
        } else if (months === 0 && today.get(Calendar.DATE) < dob.get(Calendar.DATE)) {
            years--
            months = 11
        }
        //Calculate the days
        var days : Int
        if (today.get(Calendar.DATE) > dob.get(Calendar.DATE))
            days = today.get(Calendar.DATE) - dob.get(Calendar.DATE)
        else if (today.get(Calendar.DATE) < dob.get(Calendar.DATE)) {
            val currentDay: Int = today.get(Calendar.DAY_OF_MONTH)
            today.add(Calendar.MONTH, -1)
            days =
                today.getActualMaximum(Calendar.DAY_OF_MONTH) - dob.get(Calendar.DAY_OF_MONTH) + currentDay
        } else {
            days = 0
            if (months == 12) {
                years++
                months = 0
            }
        }
        currentAge = "$firstName $lastName is $years years $months months and $days days old."
    }



    override fun onCleared() {
        super.onCleared()
        Log.i(MainActivityViewModel::class.simpleName, "MainActivityViewModel destroyed!")
    }
}