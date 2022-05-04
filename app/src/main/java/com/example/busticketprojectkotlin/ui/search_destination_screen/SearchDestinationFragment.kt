package com.example.busticketprojectkotlin.ui.search_destination_screen

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.busticketprojectkotlin.adapter.citysearch.CitySearchAdapter
import com.example.busticketprojectkotlin.base.BaseFragment
import com.example.busticketprojectkotlin.common.Resource
import com.example.busticketprojectkotlin.databinding.FragmentSearchDestinationBinding
import com.example.busticketprojectkotlin.util.ArrivalOrDepartureOptions
import com.example.busticketprojectkotlin.util.LogData
import com.example.busticketprojectkotlin.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchDestinationFragment : BaseFragment<FragmentSearchDestinationBinding, SearchDestinationViewModel>(
    FragmentSearchDestinationBinding::inflate
) {

    override val viewModel by viewModels<SearchDestinationViewModel>()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val args by navArgs<SearchDestinationFragmentArgs>()

    private lateinit var cityNameListAdapter: CitySearchAdapter




    override fun onCreateFinished() {
        initRecyclerView()
    }

    override fun initializeListeners() {
        cityNameListAdapter.onCityNameClicked { cityModel ->

            if (args.arrivalOrDepartureOptions == ArrivalOrDepartureOptions.SELECTED_DEPARTURE.ordinal) {

                sharedViewModel.setCityDeparture(cityName = cityModel.cityName)

                val action = SearchDestinationFragmentDirections.actionSearchDestinationFragmentToSearchFragment(
                        cityModel.cityName,
                        ArrivalOrDepartureOptions.SELECTED_DEPARTURE.ordinal
                    )

                Navigation.findNavController(requireView()).navigate(action)

            } else if (args.arrivalOrDepartureOptions == ArrivalOrDepartureOptions.SELECTED_ARRIVAL.ordinal) {

                sharedViewModel.setCityArrival(cityName = cityModel.cityName)

                val action = SearchDestinationFragmentDirections.actionSearchDestinationFragmentToSearchFragment(
                        cityModel.cityName,
                        ArrivalOrDepartureOptions.SELECTED_ARRIVAL.ordinal
                    )
                Navigation.findNavController(requireView()).navigate(action)
            }

        }
    }

    override fun observeEvents() {
        viewModel.cityList.observe(viewLifecycleOwner) { data ->
            when (data) {
                is Resource.Success -> {
                    binding.progress.visibility = View.GONE
                    data.data?.let { cityNameListAdapter.setAllCityName(it) }
                }
                is Resource.Error -> {
                    binding.progress.visibility = View.GONE
                    LogData("onViewCreated: ERROR " + data.message)
                }
                is Resource.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                    LogData("onViewCreated: LOADING..")

                }
            }
        }
        viewModel.setSearchCityWithDataBase(binding.searchEditText)
    }

    private fun initRecyclerView() {
        cityNameListAdapter = CitySearchAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = cityNameListAdapter
    }

}