package com.example.busticketprojectkotlin.services.repostory

import com.example.busticketprojectkotlin.model.BustListModel
import com.example.busticketprojectkotlin.model.CityModelDto
import com.example.busticketprojectkotlin.services.city_search_api.ICitySearchApi
import retrofit2.Response
import javax.inject.Inject

class CitySearchRepository @Inject constructor(
    private val ICitySearchApi : ICitySearchApi
) {

    suspend fun getCityList() : Response<List<CityModelDto>>{
        return ICitySearchApi.getCityList()
    }

    suspend fun getBusSeatList() : Response<List<BustListModel>>{
        return ICitySearchApi.getBusSeatRequest()
    }
}