package com.example.busticketprojectkotlin.adapter.buslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.busticketprojectkotlin.databinding.BusListRecylerRowBinding
import com.example.busticketprojectkotlin.model.BustListModel


class BusListRecyclerAdapter : RecyclerView.Adapter<BusListRecyclerAdapter.BusListViewHolder>() {

    private var busList = emptyList<BustListModel>()

    inner class BusListViewHolder(val binding: BusListRecylerRowBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusListViewHolder {
        val binding = BusListRecylerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BusListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BusListViewHolder, position: Int) {
        holder.binding.busList = busList[position]

        holder.itemView.setOnClickListener {
            setBusCardClickListener?.let {
                it(busList[position])
            }
        }


    }

    override fun getItemCount() = busList.size

    private var setBusCardClickListener: ((busCard: BustListModel) -> Unit)? = null

    fun onBusCardClicked(listener: (BustListModel) -> Unit) {
        setBusCardClickListener = listener
    }

    fun setBusListData(newList: List<BustListModel>) {
        busList = newList
        notifyDataSetChanged()
    }

}