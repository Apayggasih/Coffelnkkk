package com.uaspm2.coffelnkk.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.uaspm2.coffelnkk.activity.LoginActivity
import com.uaspm2.coffelnkk.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            binding = FragmentProfileBinding.inflate(inflater, container, false)

        val user = FirebaseAuth.getInstance().currentUser!!
        binding.username.text = user.displayName
        Glide.with(this)
            .load(user.photoUrl)
            .into(binding.gambar)

        binding.logoutButton.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .build()
            GoogleSignIn.getClient(requireActivity(), gso).signOut()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()
        }

        return (binding.root)
    }
}