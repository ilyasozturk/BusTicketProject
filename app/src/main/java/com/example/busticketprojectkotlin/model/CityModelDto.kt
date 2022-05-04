package com.example.busticketprojectkotlin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "city_name")
data class CityModelDto(

    @ColumnInfo(name = "cityname")
    @SerializedName(value = "cityname")
    val cityName :String,

    @ColumnInfo(name = "citynameshort")
    @SerializedName(value = "citycode")
    val countryCode: Int
) {
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0

}
//fun CityModelDto.toCityModel(): CityModel{
//    return CityModel(
//        cityName = cityName,
//        countryCode = countryCode
//    )
//}