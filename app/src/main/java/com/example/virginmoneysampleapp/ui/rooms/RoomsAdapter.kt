package com.example.virginmoneysampleapp.ui.rooms

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.virginmoneysampleapp.R
import com.example.virginmoneysampleapp.model.RoomModelItem
import kotlinx.android.synthetic.main.recycler_layout_rooms.view.*


class RoomsAdapter(private val items:MutableList<RoomModelItem>,val context: Context):RecyclerView.Adapter<RoomsAdapter.RecyclerViewHolder>() {

    class RecyclerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_layout_rooms,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val curItem = items[position]

        holder.itemView.apply {
            roomID.text= "Room Id :"+ curItem.id
            timeID.text= "Set At:"+curItem.created_at
            maxOccID.text="Max Occupancy:"+curItem.max_occupancy.toString()


            if (curItem.is_occupied){
                checkBoxOccupied.text = "Occupied"
                checkBoxOccupied.isChecked = false
            }
            else{
                checkBoxOccupied.text = "Available"
                checkBoxOccupied.isChecked = true
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}