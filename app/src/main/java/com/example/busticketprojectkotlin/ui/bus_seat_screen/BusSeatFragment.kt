package com.example.busticketprojectkotlin.ui.bus_seat_screen

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.busticketprojectkotlin.databinding.FragmentBusSeatBinding
import com.example.busticketprojectkotlin.model.SelectedSeatModel
import com.example.busticketprojectkotlin.util.downloadImage
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.ArrayList


class BusSeatFragment : BottomSheetDialogFragment() {

    private lateinit var _binding: FragmentBusSeatBinding
    private val binding get() = _binding

    private val viewModel by viewModels<BusSeatViewModel>()
    private val args by navArgs<BusSeatFragmentArgs>()

    private var selectedSeatSize = 0
    private var selectedSeatNoList = ArrayList<String>()
    private var selectedSeatGenderList = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBusSeatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val behavior = BottomSheetBehavior.from(requireView().parent as View)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnContinue.isEnabled = false
        binding.btnContinue.isClickable = false
        observeLiveData()
        fillViewData()


    }


    private fun fillViewData() {
        binding.apply {
            imgBusLogo.downloadImage(args.busListModel.companyImage)
            journeyTime.text = args.busListModel.journeyTime
            seatPrice.text = args.busListModel.price
            seatInfo.text = args.busListModel.seatInfo
            departureArrivalInfo.text = args.busListModel.departureArrivalInfo
            departureTime.text = args.busListModel.departureTime

        }
        binding.btnContinue.setOnClickListener {

            val action =
                BusSeatFragmentDirections.actionBusSeatFragmentToSelectedSeatDetailFragment(
                    args.busListModel, binding.totalPrice.text.toString(),
                    selectedSeatSize,
                    selectedSeatNoList.toTypedArray(),
                    selectedSeatGenderList.toTypedArray()
                )
            findNavController().navigate(action)
        }
    }


    private fun observeLiveData() {


        if (args.busListModel.seatInfo == "2+1") {
            context?.let {
                viewModel.busSeatArea(
                    it,
                    binding.layoutSeat,
                    1,
                    args.busListModel.price
                )
            }
        } else if (args.busListModel.seatInfo == "2+2") {
            context?.let {
                viewModel.busSeatArea(
                    it,
                    binding.layoutSeat,
                    2,
                    args.busListModel.price
                )
            }
        }

        viewModel.selectedSeatList.observe(viewLifecycleOwner) {

            selectedSeatSize = it.size
            selectedSeatNoList.clear()
            selectedSeatGenderList.clear()
            for (seatsNo in it) {
                selectedSeatNoList.add(seatsNo.seatId.toString())
                selectedSeatGenderList.add(seatsNo.seatGender.toString())
            }
        }

        viewModel.seatTotalPrice.observe(viewLifecycleOwner) {
            binding.totalPrice.text = it.toString().replace(it.toString(), "$it,00TL")

        }
        viewModel.selectedSeats.observe(viewLifecycleOwner) {
            binding.linearLayoutSelectedSeat.addView(it)


        }
        viewModel.deleteSeats.observe(viewLifecycleOwner) {
            binding.linearLayoutSelectedSeat.removeView(it)
        }
        viewModel.isSelectedSeat.observe(viewLifecycleOwner) {
            binding.btnContinue.isEnabled = it
            binding.btnContinue.isClickable = it
        }


    }

}