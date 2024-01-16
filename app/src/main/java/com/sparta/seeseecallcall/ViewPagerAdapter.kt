package com.sparta.seeseecallcall

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    private val TAB_COUNT = 3
    override fun getItemCount(): Int {
        return TAB_COUNT // 탭의 총 개수
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ContactListFragment()
            1 -> ContactBookmarkFragment()
            2 -> MyPageFragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}
