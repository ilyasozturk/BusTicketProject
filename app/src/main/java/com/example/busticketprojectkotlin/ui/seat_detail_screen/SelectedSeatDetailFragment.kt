package com.example.busticketprojectkotlin.ui.seat_detail_screen


import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.busticketprojectkotlin.adapter.selected_seat.ViewPagerAdapter
import com.example.busticketprojectkotlin.base.BaseFragment
import com.example.busticketprojectkotlin.databinding.FragmentSelectedSeatDetailBinding
import com.example.busticketprojectkotlin.payment_card.views.CreditCardExpiryTextWatcher
import com.example.busticketprojectkotlin.util.downloadImage
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.textfield.TextInputEditText

class SelectedSeatDetailFragment :
    BaseFragment<FragmentSelectedSeatDetailBinding, SeatDetailViewModel>(
        FragmentSelectedSeatDetailBinding::inflate
    ) {

    override val viewModel by viewModels<SeatDetailViewModel>()
    private val args by navArgs<SelectedSeatDetailFragmentArgs>()

    override fun onCreateFinished() {
        bindView()
        bindSeat()
    }



    override fun initializeListeners() {
        binding.txtInputCardExpiration.addTextChangedListener(CreditCardExpiryTextWatcher(binding.txtInputCardExpiration))
        binding.btnPayment.setOnClickListener {
            checkSeat()
        }
    }

    override fun observeEvents() {
    }


    private fun bindView() {

        binding.apply {
            tvCompanyLogo.downloadImage(args.busListModel.companyImage)
            tvDate.text = "13/02/2022"
            tvDepartureTime.text = args.busListModel.departureTime
            tvSeatInfo.text = args.busListModel.seatInfo
            tvTotalPrice.text = args.busListModel.price
            txtDisplayPrice.text=args.totalPrice
        }
    }

    private fun bindSeat() {
        val adapter = ViewPagerAdapter(requireActivity().supportFragmentManager,lifecycle,args.selectedSeatSize)
        binding.seatViewPager.adapter = adapter

        TabLayoutMediator(binding.seatTab, binding.seatViewPager) { tab, position ->
            when (position) {
                0 -> tab.text = args.selectedSeatNoList?.get(0) + ".Koltuk".getGender(0)
                1 -> tab.text = args.selectedSeatNoList?.get(1) + ".Koltuk".getGender(1)
                2 -> tab.text = args.selectedSeatNoList?.get(2) + ".Koltuk".getGender(2)
                3 -> tab.text = args.selectedSeatNoList?.get(3) + ".Koltuk".getGender(3)
            }
        }.attach()


    }
    private fun checkSeat(){

    }



    private fun String.getGender(index: Int): String {
        val result = args.selectedSeatGenderList?.get(index)
        return if (result != "1") {
            "$this(Erkek)"
        } else "$this(Kadın)"
    }
}