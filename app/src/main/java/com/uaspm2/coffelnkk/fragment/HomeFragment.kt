package com.uaspm2.coffelnkk.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.uaspm2.coffelnkk.R
import com.uaspm2.coffelnkk.adapters.ImageAdapter
import com.uaspm2.coffelnkk.adapters.LogoAdapter
import com.uaspm2.coffelnkk.data.ImageItem
import com.uaspm2.coffelnkk.databinding.FragmentHomeBinding
import java.util.UUID

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val imageRV = binding.root.findViewById<RecyclerView>(R.id.imageRV)

        val imageList = arrayListOf(
            ImageItem(
                UUID.randomUUID().toString(),
                "https://dikemas.com/uploads/2020/09/Ini-Dia-Beberapa-Jenis-Minuman-Kopi-Yang-Perlu-Kamu-Ketahui-720x442.jpg"
            ),
            ImageItem(
                UUID.randomUUID().toString(),
                "https://assets.unileversolutions.com/v1/63465882.png"
            ),
            ImageItem(
                UUID.randomUUID().toString(),
                "https://assets.unileversolutions.com/v1/63465882.png"
            )
        )

        val imageAdapter = ImageAdapter()
        imageRV.adapter = imageAdapter
        imageAdapter.submitList(imageList)

        binding.RecyclerViewItemCafe.adapter = LogoAdapter {

        }

        return binding.root
    }

}
