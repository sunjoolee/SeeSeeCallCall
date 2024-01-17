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
import com.sparta.seeseecallcall.databinding.FragmentContactListBinding

class ContactListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentContactListBinding.inflate(inflater, container, false)

        val adapter = MyAdapter(contactList)
        binding.recyclerviewList.adapter = adapter
        binding.recyclerviewList.layoutManager = LinearLayoutManager(context)
        binding.recyclerviewList.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayout.VERTICAL
            )
        )

        adapter.itemClick = object : MyAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                Log.d(Constants.TAG_List, "position: $position")

                val contactData = contactList[position]
                val contactDetailFragment = ContactDetailFragment.newInstance(contactData)
                Log.d("보내는 Detail 프래그먼트", contactList[position].mbti)

                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment, contactDetailFragment)
                    .addToBackStack(null)
                    .commit()

            }

            override fun onStarClick(view: View, position: Int) {
                contactList[position].run {
                    favorite = !favorite

                    if (favorite) contactBookmarkList.add(this)
                    else contactBookmarkList.remove(this)
                }
                contactBookmarkList.sortBy { it.name }
                adapter.notifyDataSetChanged()
            }
        }

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(text: Editable?) {
                Log.d(Constants.TAG_List, "afterTextChanged, ${text.toString()}")
                adapter.ChangeDataset(
                    if (text.isNullOrBlank()) contactList
                    else getFilteredList(text.toString())
                )
            }
        })

        return binding.root
    }

    override fun onResume(){
        Log.d(Constants.TAG_List, "ContactListFragmentList onResume()")

        this.view?.findViewById<RecyclerView>(R.id.recyclerview_list)?.adapter?.notifyDataSetChanged()
        super.onResume()
    }

    private fun getFilteredList(searchText: String): MutableList<Contact> {
        val filteredList = mutableListOf<Contact>()
        return filteredList.apply {
            contactList.forEach {
                //이름으로 검색
                if (it.name.contains(searchText)) add(it)
                //mbti로 검색
                if (it.mbti.contains(searchText.toUpperCase())) add(it)
            }
        }
    }
}