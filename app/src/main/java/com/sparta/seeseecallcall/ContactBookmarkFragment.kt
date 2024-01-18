package com.sparta.seeseecallcall

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sparta.seeseecallcall.data.Contact
import com.sparta.seeseecallcall.data.ContactManager.contactBookmarkList
import com.sparta.seeseecallcall.data.ContactManager.contactList
import com.sparta.seeseecallcall.databinding.FragmentContactBookmarkBinding

class ContactBookmarkFragment : Fragment() {

    private val adapter by lazy { MyAdapter(contactBookmarkList) }
    private val TAG = "ContactBookmarkFragment"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentContactBookmarkBinding.inflate(inflater, container, false)

        binding.recyclerviewBookmark.adapter = adapter
        binding.recyclerviewBookmark.layoutManager = LinearLayoutManager(context)
        binding.recyclerviewBookmark.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))

        adapter.itemClick = object : MyAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {
                Log.d(TAG, "position: $position")
                //TODO 연락처 객체 넘기며, 연락처 상세 페이지로 이동
            }
            override fun onStarClick(view: View, position: Int) {
                contactList.find { it === contactBookmarkList[position] }?.run{
                    favorite = !favorite
                }
                contactBookmarkList.removeAt(position)
                adapter.notifyItemRemoved(position)
            }
        }

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(text: Editable?) {
                Log.d(TAG, "afterTextChanged, ${text.toString()}")
                adapter.ChangeDataset(
                    if (text.isNullOrBlank()) contactBookmarkList
                    else getFilteredList(text.toString())
                )
            }
        })

        return binding.root
    }

    override fun onResume(){
        Log.d(TAG, "ContactBookmarkFragmentList onResume()")

        adapter?.notifyDataSetChanged()
        super.onResume()
    }

    private fun getFilteredList(searchText: String): MutableList<Contact> {
        val filteredList = mutableListOf<Contact>()
        return filteredList.apply {
            contactBookmarkList.forEach {
                //이름으로 검색
                if (it.name.contains(searchText)) add(it)
                //mbti로 검색
                if (it.mbti.contains(searchText.toUpperCase())) add(it)
            }
        }
    }
}