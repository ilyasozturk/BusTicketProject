package com.example.busticketprojectkotlin.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BustListModel(
    var companyImage : String,
    var departureTime: String,
    var journeyTime : String,
    var price : String,
    var seatInfo : String,
    var departureArrivalInfo : String,
    var totalPrice: String

) : Parcelable {
}