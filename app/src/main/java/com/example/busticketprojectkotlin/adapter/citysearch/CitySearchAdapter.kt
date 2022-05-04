package com.example.busticketprojectkotlin.adapter.citysearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.busticketprojectkotlin.databinding.CitySearchRecyclerRowBinding
import com.example.busticketprojectkotlin.model.CityModelDto

class CitySearchAdapter: RecyclerView.Adapter<CitySearchAdapter.MViewHolder>() {

    private var cityList = emptyList<CityModelDto>()

     class MViewHolder(val binding: CitySearchRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){

         companion object{
             fun from(parent: ViewGroup) :MViewHolder{
                 val layoutInflater = LayoutInflater.from(parent.context)
                 val binding = CitySearchRecyclerRowBinding.inflate(layoutInflater,parent,false)
                 return MViewHolder(binding)
             }
         }
     }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder = MViewHolder.from(parent)

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        // val cityName = differ.currentList[position]
        holder.binding.apply {
            city = cityList[position]
            this.txtCityName.text = cityList[position].cityName
        }

        holder.itemView.setOnClickListener {
            setCityNameClickListener?.let {
                it(cityList[position])
            }
        }

    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    private var setCityNameClickListener: ((cityName: CityModelDto) -> Unit)? = null

    fun onCityNameClicked(listener: (CityModelDto) -> Unit) {
        setCityNameClickListener = listener
    }

    fun setAllCityName(newList: List<CityModelDto>) {
        cityList = newList
        notifyDataSetChanged()


    }

    private val diffUtil = object : DiffUtil.ItemCallback<CityModelDto>() {
        override fun areItemsTheSame(oldItem: CityModelDto, newItem: CityModelDto): Boolean {

            return oldItem.cityName == newItem.cityName
        }

        override fun areContentsTheSame(oldItem: CityModelDto, newItem: CityModelDto): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, diffUtil)
}