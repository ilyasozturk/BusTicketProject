package com.example.busticketprojectkotlin.ui.bus_list_screen


import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.busticketprojectkotlin.adapter.buslist.BusListRecyclerAdapter
import com.example.busticketprojectkotlin.base.BaseFragment
import com.example.busticketprojectkotlin.common.Resource
import com.example.busticketprojectkotlin.databinding.FragmentBusListBinding
import com.example.busticketprojectkotlin.util.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusListFragment : BaseFragment<FragmentBusListBinding, BusListViewModel>(
    FragmentBusListBinding::inflate
) {

    private  var rvBusListAdapter = BusListRecyclerAdapter()
    override val viewModel by viewModels<BusListViewModel>()


    override fun onCreateFinished() {
        initRecyclerViewAdapter()

    }

    override fun initializeListeners() {

        rvBusListAdapter.onBusCardClicked {
            val action = BusListFragmentDirections.actionBusListFragmentToBusSeatFragment(it)
            Navigation.findNavController(requireView()).navigate(action)
        }

    }

    override fun observeEvents() {
        viewModel.busList.observe(viewLifecycleOwner) { data ->

            when(data){
                is Resource.Success -> {

                    LoadingDialog.hideLoading()
                    data.data?.let { rvBusListAdapter.setBusListData(it) }
                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {
                    LoadingDialog.displayLoadingWithText(context,"Seferler Yükleniyor",false)

                }
            }

        }


    }

    private fun initRecyclerViewAdapter(){
        binding.bustListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = rvBusListAdapter
        }
    }

}