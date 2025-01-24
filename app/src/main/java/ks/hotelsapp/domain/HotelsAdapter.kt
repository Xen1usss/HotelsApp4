package ks.hotelsapp.domain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ks.hotelsapp.R

class HotelsAdapter(private val items: List<Hotel>) :
    RecyclerView.Adapter<HotelsAdapter.HotelViewHolder>() {

    class HotelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val rating: RatingBar = view.findViewById(R.id.hotel_rating)
        val available: TextView = view.findViewById(R.id.avaliable)
        val distance: TextView = view.findViewById(R.id.distance)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.hotel_item, parent, false)
        return HotelViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        val item = items[position]
        holder.name.text = item.name
        holder.rating.rating = item.stars
        holder.available.text = "Свободные номера: ${item.availableRooms}"
        holder.distance.text = "Расстояние от центра: ${item.distance}"
    }

    override fun getItemCount(): Int = items.size
}
