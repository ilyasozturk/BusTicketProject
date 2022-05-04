package com.example.busticketprojectkotlin.ui.bus_list_screen


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.busticketprojectkotlin.common.Resource
import com.example.busticketprojectkotlin.model.BustListModel
import com.example.busticketprojectkotlin.services.repostory.CitySearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class BusListViewModel @Inject constructor(
    private val citySearchRepository: CitySearchRepository
) : ViewModel() {
    private val _busList = MutableLiveData<Resource<List<BustListModel>>>()
    val busList : LiveData<Resource<List<BustListModel>>> =_busList

    init {
        getManualBusList()
    }



 private fun getBusSeat(){
     _busList.postValue(Resource.Loading())
     viewModelScope.launch {
         val response = citySearchRepository.getBusSeatList()
         _busList.postValue(getBusSeatListResponse(response))
     }
 }
    private fun getBusSeatListResponse(response: Response<List<BustListModel>>): Resource<List<BustListModel>> {
        if (response.isSuccessful) {
            response.body().let {

                return Resource.Success(it)
            }
        }else
        return Resource.Error(message = response.errorBody().toString())

    }



   private fun getManualBusList() {

        val listOfBusServices = listOf(
            BustListModel("https://cdn1.tasarlatasarlat.com/desings/f0/d6/c49/0507cb4c5765c173dd1f99e0a.jpg",
                "10:00",
                "2s 2dk",
                "250 TL",
                "2+2",
                "İstanbul Otogarı ",
                ""
            ),
            BustListModel("https://files.sikayetvar.com/lg/cmp/15/159417.png?1609773943",
                "05:00",
                "10s 2dk",
                "150 TL",
                "2+1",
                "İstanbul Otogarı",
            ""
            ),
            BustListModel("https://cdn-cf.cms.flixbus.com/drupal-assets/ogimage/kamilkoc.png",
                "13:00",
                "3s 2dk",
                "80 TL",
                "2+2",
                "İstanbul Otogarı",
                ""
            ),
            BustListModel("https://s3.eu-central-1.amazonaws.com/static.obilet.com/images/partner/2233-sm.png",
                "19:00",
                "18s 2dk",
                "400 TL",
                "2+2",
                "İstanbul Otogarı",
                ""
            )

        )

       viewModelScope.launch {
           _busList.postValue(Resource.Loading())
           delay(2000)
           _busList.postValue(Resource.Success(listOfBusServices))
       }

    }





}