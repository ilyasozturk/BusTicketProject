package com.example.busticketprojectkotlin.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.busticketprojectkotlin.model.CityModelDto

@Dao
interface ICityNameDAO {

    @Insert
    suspend fun insertAll(vararg cityNameDto : CityModelDto) :List<Long>

    @Query("SELECT * FROM city_name")
    suspend fun getAllCityName():List<CityModelDto>

    @Query("SELECT * FROM city_name WHERE uuid =:getCityId")
    suspend fun getCity(getCityId: Int) : CityModelDto

    @Query("SELECT * FROM city_name WHERE cityname LIKE :search|| '%'")
    suspend fun searchCity(search : String) : List<CityModelDto>

    @Query("DELETE FROM city_name")
    suspend fun deleteAllCityNameTable()
}