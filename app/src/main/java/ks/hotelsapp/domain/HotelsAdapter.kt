package ks.hotelsapp.domain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ks.hotelsapp.databinding.HotelItemBinding

class HotelsAdapter(private val items: List<Hotel>) :
    RecyclerView.Adapter<HotelsAdapter.HotelViewHolder>() {

    class HotelViewHolder(private val binding: HotelItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Hotel) {
            binding.name.text = item.name
            binding.hotelRating.rating = item.stars
            binding.avaliable.text = "Свободные номера: ${item.availableSuitesCount}"
            binding.distance.text = "Расстояние от центра: ${item.distance}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val binding = HotelItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HotelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size
}