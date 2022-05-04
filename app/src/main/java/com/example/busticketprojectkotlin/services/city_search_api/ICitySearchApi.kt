package com.example.busticketprojectkotlin.services.city_search_api

import com.example.busticketprojectkotlin.model.BustListModel
import com.example.busticketprojectkotlin.model.CityModelDto
import retrofit2.Response
import retrofit2.http.GET

interface ICitySearchApi {

    @GET("ilyasozturk/cityofturkey/main/cities_of_turkey.json")
    suspend fun getCityList () : Response<List<CityModelDto>>

    @GET("test/test")
    suspend fun getBusSeatRequest() : Response<List<BustListModel>>


}