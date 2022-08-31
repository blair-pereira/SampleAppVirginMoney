package com.example.virginmoneysampleapp.ui.people

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.virginmoneysampleapp.R
import com.example.virginmoneysampleapp.model.PeopleModelItem
import kotlinx.android.synthetic.main.recycler_layout_people.view.*

class PeopleAdapter(private val items:MutableList<PeopleModelItem>,val context: Context):RecyclerView.Adapter<PeopleAdapter.RecyclerViewHolder>() {

    class RecyclerViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_layout_people,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        var curItem = items[position]

        Glide.with(context).load(curItem.avatar)
            .apply(RequestOptions().placeholder(R.drawable.user_image)).into(holder.itemView.imageURLID)

        holder.itemView.apply {
            jobtitleID.text = curItem.jobTitle
            nameID.text = curItem.firstName +" "+curItem.lastName
            emailID.text = curItem.email
        }

        //holder.itemView
    }

    override fun getItemCount(): Int {
        return items.size
    }
}