package com.example.busticketprojectkotlin.ui.selected_seat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.busticketprojectkotlin.R
import com.example.busticketprojectkotlin.databinding.FragmentSeatTwoBinding

class SeatTwo: Fragment(R.layout.fragment_seat_two) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       FragmentSeatTwoBinding.bind(view)

    }

}