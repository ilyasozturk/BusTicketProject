package com.example.busticketprojectkotlin.ui.selected_seat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.busticketprojectkotlin.R
import com.example.busticketprojectkotlin.databinding.FragmentSeatOneBinding

class SeatOne : Fragment(R.layout.fragment_seat_one) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       FragmentSeatOneBinding.bind(view)

    }


}

