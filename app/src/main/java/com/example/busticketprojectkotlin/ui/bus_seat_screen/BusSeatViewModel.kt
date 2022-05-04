package com.example.busticketprojectkotlin.ui.bus_seat_screen

import android.content.Context
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.busticketprojectkotlin.model.SelectedSeatModel
import com.example.busticketprojectkotlin.util.BusSeat

class BusSeatViewModel : ViewModel() {

    private val seats = MutableLiveData<ArrayList<SelectedSeatModel>>()
    private val seatsPattern = MutableLiveData<String>()
    val selectedSeatList = MutableLiveData<ArrayList<SelectedSeatModel>>()
    val selectedSeats = MutableLiveData<TextView>()
    val deleteSeats = MutableLiveData<TextView>()
    val seatTotalPrice = MutableLiveData<Int>()
    val isSelectedSeat = MutableLiveData<Boolean>()




    fun busSeatArea(
        context: Context,
        viewGroup: ViewGroup,
        seatLayout: Int,
        selectedSeatPrice: String
    ) {
        // seatLayout =1 2+1 koltuk düzeni
        // seatLayout =2 2+2 koltuk düzeni

        val bus = BusSeat(context, this, viewGroup, selectedSeatPrice)
        bus.makeSeat(seatsPattern(seatLayout))
        seats.value = bus.getSeatList()
        seatTotalPrice.value = bus.getTotalSeatPrice()

    }


    private fun seatsPattern(seatLayout: Int): String {
        return when (seatLayout) {
            1 -> ("WW__M/"
                    + "WM__E/"
                    + "EE__W/"
                    + "MM__M/"
                    + "EE__E/"
                    + "EE__E/"
                    + "WW__W/"
                    + "ME__E/"
                    + "WE__W/"
                    + "EE__E/"
                    + "EE__E/"
                    + "EM__E/"
                    + "EW__M/")

            2 -> ("WW_ME/"
                    + "WM_EE/"
                    + "EE_WE/"
                    + "MM_ME/"
                    + "EE_EE/"
                    + "EE_EE/"
                    + "WW_WE/"
                    + "ME_EE/"
                    + "WE_WE/"
                    + "EE_EE/"
                    + "EE_EE/"
                    + "EM_EE/"
                    + "EW_ME/")
            else -> "empty"

        }

    }
}