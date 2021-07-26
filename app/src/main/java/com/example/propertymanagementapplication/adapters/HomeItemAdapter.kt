package com.example.propertymanagementapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.propertymanagementapplication.R
import com.example.propertymanagementapplication.databinding.HolderHomeItemAdapterBinding
import com.example.propertymanagementapplication.models.HomeItem

class HomeItemAdapter(var mList: ArrayList<HomeItem>): RecyclerView.Adapter<HomeItemAdapter.HomeItemViewHolder>() {
    inner class HomeItemViewHolder(var binding: HolderHomeItemAdapterBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(homeItem: HomeItem){
                binding.home = homeItem
                binding.homeItemIvImage.setImageResource(homeItem.icon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<HolderHomeItemAdapterBinding>(inflate, R.layout.holder_home_item_adapter, parent, false)
        return HomeItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeItemViewHolder, position: Int) {
        holder.bind(mList[position])

        if (this::onItemClickListener.isInitialized) {
            holder.binding.homeItemIvImage.setOnClickListener{
                onItemClickListener(mList[position])
            }
            holder.binding.homeItemTvName.setOnClickListener{
                onItemClickListener(mList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    lateinit var onItemClickListener:(homeItem:HomeItem) -> Unit
}