package com.example.busticketprojectkotlin.ui.selected_seat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.busticketprojectkotlin.R
import com.example.busticketprojectkotlin.databinding.FragmentSeatFourBinding

class SeatFour: Fragment(R.layout.fragment_seat_four) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentSeatFourBinding.bind(view)
    }
}