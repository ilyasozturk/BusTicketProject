package com.example.busticketprojectkotlin.ui.search_destination_screen

import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.busticketprojectkotlin.common.Resource
import com.example.busticketprojectkotlin.db.repostoriy.DatabaseRepo
import com.example.busticketprojectkotlin.model.CityModelDto
import com.example.busticketprojectkotlin.services.repostory.CitySearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchDestinationViewModel @Inject constructor(
    private val citySearchRepository: CitySearchRepository,
    private val databaseRepo: DatabaseRepo
) : ViewModel() {

    private val _cityList = MutableLiveData<Resource<List<CityModelDto>>>()
    val cityList: LiveData<Resource<List<CityModelDto>>> = _cityList


    init {
        getCityNameList()
    }


    private fun getCityNameList() {
        _cityList.postValue(Resource.Loading())
        viewModelScope.launch {
            val response = citySearchRepository.getCityList()
            _cityList.postValue(cityNameListResponse(response))
        }
    }

    // Retrofit get api call
    private fun cityNameListResponse(response: Response<List<CityModelDto>>): Resource<List<CityModelDto>> {
        if (response.isSuccessful) {
            response.body().let {
                if (it != null) {
                    insertAllCityListDB(it)
                }
                return Resource.Success(it)
            }
        }
        return Resource.Error(message = response.errorBody().toString())

    }


    fun setSearchCityWithDataBase(search: EditText) {
        search.doOnTextChanged { text, _, _, _ ->
            if (text != null) {
                println("Arama Sonucu ${search.text}")
                viewModelScope.launch {
                    if (text.length >= 2) {
                        val list = databaseRepo.searchCity(search.text.toString())
                        _cityList.postValue(Resource.Success(list))

                    } else if (text.isEmpty()) {
                        val allData = databaseRepo.getAllData()
                        _cityList.postValue(Resource.Success(allData))
                    }
                }

            }
        }


    }


    //Insert All City Name Local Database Function

    private fun insertAllCityListDB(cityNameListDto: List<CityModelDto>) {
        viewModelScope.launch {
            databaseRepo.deleteAllData()
            val uuidList = databaseRepo.addAllData(*cityNameListDto.toTypedArray())
            var i = 0
            while (i < cityNameListDto.size) {
                cityNameListDto[i].uuid = uuidList[i].toInt()
                i++
            }
            _cityList.postValue(Resource.Success(cityNameListDto))
        }


    }

}