package com.example.virginmoneysampleapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.virginmoneysampleapp.R
import com.example.virginmoneysampleapp.databinding.FragmentDetailBinding
import java.util.ArrayList

class DetailFragment:Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailBinding.bind(view)


        val myData_list = requireArguments().get("user_details") as ArrayList<String>


        Glide.with(requireActivity()).load(myData_list[3])
            .apply(RequestOptions().placeholder(R.drawable.user_image)).into(binding.imageView)

        binding.nameDetailID.text = myData_list[1] + " " + myData_list[2]
        binding.jobTitle.text = myData_list[0]
        binding.emailtv.text = myData_list[4]
        binding.favcolorID.text = "Favourite Color: " + myData_list[5]
        binding.userID.text = "User ID: " + myData_list[6]
    }
}