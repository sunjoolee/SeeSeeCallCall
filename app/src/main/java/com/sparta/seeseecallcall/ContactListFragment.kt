package com.sparta.seeseecallcall

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sparta.seeseecallcall.data.ContactManager
import com.sparta.seeseecallcall.data.ContactManager.contactBookmarkList
import com.sparta.seeseecallcall.data.ContactManager.contactList
import com.sparta.seeseecallcall.databinding.FragmentContactListBinding

class ContactListFragment : Fragment() {
    private val TAG = "ContactListFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentContactListBinding.inflate(inflater, container, false)

        val adapter = MyAdapter(contactList)
        binding.recyclerviewList.adapter = adapter
        binding.recyclerviewList.layoutManager = LinearLayoutManager(context)

        adapter.itemClick = object : MyAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {
                Log.d(TAG, "position: $position")
                //TODO 연락처 객체 넘기며, 연락처 상세 페이지로 이동
            }

            override fun onStarClick(view: View, position: Int) {
                contactList[position].run{
                    favorite = !favorite

                    if(favorite) contactBookmarkList.add(this)
                    else contactBookmarkList.remove(this)
                }
                contactBookmarkList.sortBy { it.name }
                adapter.notifyDataSetChanged()
            }
        }

        return binding.root
    }

    override fun onResume(){
        Log.d(TAG, "ContactListFragmentList onResume()")

        this.view?.findViewById<RecyclerView>(R.id.recyclerview_list)?.adapter?.notifyDataSetChanged()
        super.onResume()
    }
}