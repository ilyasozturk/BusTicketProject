package com.example.busticketprojectkotlin.db.repostoriy

import com.example.busticketprojectkotlin.db.dao.ICityNameDAO
import com.example.busticketprojectkotlin.model.CityModelDto
import javax.inject.Inject

class DatabaseRepo @Inject constructor(
    private val cityNameDAO: ICityNameDAO
) {
    suspend fun getAllData(): List<CityModelDto> {
        return cityNameDAO.getAllCityName()
    }

    suspend fun searchCity(search: String): List<CityModelDto> {
        return cityNameDAO.searchCity(search)
    }

    suspend fun addAllData(vararg cityNameDto: CityModelDto): List<Long> {
        return cityNameDAO.insertAll(*cityNameDto)
    }

    suspend fun deleteAllData() {
        cityNameDAO.deleteAllCityNameTable()
    }

}