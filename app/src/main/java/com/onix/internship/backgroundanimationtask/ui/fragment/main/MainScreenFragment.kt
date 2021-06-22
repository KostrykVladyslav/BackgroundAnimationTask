package com.onix.internship.backgroundanimationtask.ui.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.onix.internship.backgroundanimationtask.databinding.FragmentMainScreenBinding

class MainScreenFragment : Fragment() {
    private lateinit var binding : FragmentMainScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainScreenBinding.inflate(inflater)
        binding.animationView.resume()
        return binding.root
    }
}