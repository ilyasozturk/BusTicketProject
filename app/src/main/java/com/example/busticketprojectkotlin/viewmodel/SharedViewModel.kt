package com.example.busticketprojectkotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel(){

    private var _cityName = MutableLiveData<String>()
    val cityName : LiveData<String> =_cityName

    private var _cityNameDeparture = MutableLiveData<String>()
    val cityNameDeparture : LiveData<String> =_cityNameDeparture

    fun setCityArrival(cityName : String){
        _cityName.value = cityName
    }

    fun setCityDeparture(cityName : String){
        _cityNameDeparture.value = cityName
    }


}