package com.example.virginmoneysampleapp.ui.people

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.virginmoneysampleapp.R
import com.example.virginmoneysampleapp.model.PeopleModelItem
import com.example.virginmoneysampleapp.ui.main.MainActivity
import kotlinx.android.synthetic.main.recycler_layout_people.view.*
import java.util.ArrayList

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

        holder.itemView.setOnClickListener(){

            val my_main_activity = (context as MainActivity)

            val myList = mutableListOf<String>(
                curItem.jobTitle,
                curItem.firstName,
                curItem.lastName,
                curItem.avatar,
                curItem.email,
                curItem.favouriteColor,
                curItem.id
            )

            val bundle = Bundle()
            bundle.putStringArrayList("user_details",myList as ArrayList<String>)

            it.findNavController().navigate(R.id.action_mainFragment_to_detailFragment, bundle)

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}