package com.example.busticketprojectkotlin.ui.home_screen


import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.busticketprojectkotlin.base.BaseFragment
import com.example.busticketprojectkotlin.databinding.FragmentSearchBinding
import com.example.busticketprojectkotlin.model.DateModel
import com.example.busticketprojectkotlin.util.ArrivalOrDepartureOptions
import com.example.busticketprojectkotlin.viewmodel.SharedViewModel


class HomeFragment : BaseFragment<FragmentSearchBinding, HomeFragmentViewModel>(
    FragmentSearchBinding::inflate
) {

    override val viewModel by viewModels<HomeFragmentViewModel>()
    private val sharedViewModel: SharedViewModel by activityViewModels()


    override fun onCreateFinished() {
    }

    override fun initializeListeners() {
        binding.btnDeparture.setOnClickListener {
            val action = HomeFragmentDirections.actionSearchFragmentToSearchDestinationFragment(
                ArrivalOrDepartureOptions.SELECTED_DEPARTURE.ordinal
            )
            Navigation.findNavController(it).navigate(action)
        }

        //Go Arrival City Search
        binding.btnArrival.setOnClickListener {
            val action = HomeFragmentDirections.actionSearchFragmentToSearchDestinationFragment(
                ArrivalOrDepartureOptions.SELECTED_ARRIVAL.ordinal
            )
            Navigation.findNavController(it).navigate(action)
        }

        observeSearchFragmentViewModel()
        binding.btnSelectDate.setOnClickListener {
            datePickerShow()
        }

        binding.btnSearch.setOnClickListener {
            val action = HomeFragmentDirections.actionSearchFragmentToBusListFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun observeEvents() {
        //Observe Shared View Model Data Function
        observeSharedViewModelData()

        viewModel.selectToday.observe(viewLifecycleOwner) {
            initBindingDate(it)
        }
    }

    private fun observeSharedViewModelData() {
        // Observe Shared View Model in Arrival City Name
        sharedViewModel.cityName.observe(viewLifecycleOwner) { arrivalCityName ->
            binding.btnArrival.text = arrivalCityName
            binding.txtCityLongNameArrival.text = arrivalCityName
        }
        // Observe Shared View Model in Departure City Name
        sharedViewModel.cityNameDeparture.observe(viewLifecycleOwner) { departureCityName ->
            binding.btnDeparture.text = departureCityName
            binding.txtCityLongNameDeparture.text = departureCityName
        }

    }

    private fun datePickerShow() {
        viewModel.datePickerDialogShow(childFragmentManager)
    }

    private fun observeSearchFragmentViewModel() {

        binding.btnToday.setOnClickListener {
            viewModel.selectToday.observe(viewLifecycleOwner, Observer {
                initBindingDate(it)
            })
        }
        binding.btnTomorrow.setOnClickListener {
            viewModel.selectTomorrow.observe(viewLifecycleOwner) {
                initBindingDate(it)
            }
        }
        viewModel.selectDatePicker.observe(viewLifecycleOwner) {
            initBindingDate(it)
        }


    }

    private fun initBindingDate(dateModel: DateModel) {
        binding.txtDate.text = dateModel.day
        binding.txtAy.text = dateModel.month
        binding.txtGunAdi.text = dateModel.year
    }
}