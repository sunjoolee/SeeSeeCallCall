package com.sparta.seeseecallcall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sparta.seeseecallcall.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = ViewPagerAdapter(this)

        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_contact -> viewPager.currentItem = 0
                R.id.navigation_bookmark -> viewPager.currentItem = 1
                R.id.navigation_myPage -> viewPager.currentItem = 2
            }
            true
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.navView.menu.getItem(position).isChecked = true
            }
        })

    }
}