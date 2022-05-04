package com.example.busticketprojectkotlin.adapter.selected_seat

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.busticketprojectkotlin.ui.selected_seat.*

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    var selectedSeatSize: Int
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return selectedSeatSize
    }

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> SeatOne()
            1 -> SeatTwo()
            2 -> SeatThree()
            3 -> SeatFour()
            else -> SeatOne()
        }

    }


}