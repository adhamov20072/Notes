package com.alimardon.app13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.alimardon.app13.databinding.ActivityMainBinding as ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: expAdapter


    val list= arrayListOf<inson>(inson("Alimardon","Adhamov"),inson("Abdulmajid","Muhammadaliyev"))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter= expAdapter()
        binding.recyclerview.adapter=adapter
        adapter.submitList(list)
    }
}