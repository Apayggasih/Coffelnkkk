package com.uaspm2.coffelnkk.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import com.uaspm2.coffelnkk.databinding.FragmentDeskripsiBinding

class DeskripsiFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val deskripsiInfo = DeskripsiFragmentArgs.fromBundle(requireArguments()).deskripsiInfo
        val binding = FragmentDeskripsiBinding.inflate(inflater, container, false)
        binding.itemJudul.text = deskripsiInfo.title
        binding.itemDeskripsi.text = deskripsiInfo.content
        Firebase.storage.getReference(deskripsiInfo.image).downloadUrl.addOnSuccessListener {
            Glide.with(binding.root.context)
                .load(it)
                .into(binding.itemGambar)
        }
        
        return binding.root
//        inflater.inflate(R.layout.fragment_deskripsi, container, false)
    }
}