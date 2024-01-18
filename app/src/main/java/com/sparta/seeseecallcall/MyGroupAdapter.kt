package com.sparta.seeseecallcall

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sparta.seeseecallcall.Constants.TAG_MY_GROUP_ADAPTER
import com.sparta.seeseecallcall.data.Contact
import com.sparta.seeseecallcall.data.ContactGroup
import com.sparta.seeseecallcall.data.ContactGroupManager.contactGroupList
import com.sparta.seeseecallcall.data.ContactManager
import com.sparta.seeseecallcall.data.ContactManager.contactBookmarkList
import com.sparta.seeseecallcall.databinding.RecyclerViewGroupItemBinding

class MyGroupAdapter(private var groupDataset: List<ContactGroup>) :
    RecyclerView.Adapter<MyGroupAdapter.MyGroupHolder>(),
    AddContactDialogFragment.OnAddContactListener {

    interface OnStartDetailFragment {
        fun onStartDetailFragment(contact: Contact)
    }
    var onStartDetailListener: OnStartDetailFragment? = null
    var onFavoriteChangeListener:OnFavoriteChangeListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyGroupHolder {
        val binding = RecyclerViewGroupItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MyGroupHolder(binding)
    }

    override fun getItemCount(): Int = groupDataset.size


    override fun onBindViewHolder(holder: MyGroupHolder, position: Int) {
        holder.run {
            groupNameTextView.text = groupDataset[position].groupName
            contactCountTextView.text = groupDataset[position].contactList.size.toString() + "명"

            val adapter = MyAdapter(groupDataset[position].contactList)
            groupRecyclerView.adapter = adapter
            groupRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
            groupRecyclerView.addItemDecoration(DividerItemDecoration(itemView.context, LinearLayout.VERTICAL))

            adapter.itemClick = object : MyAdapter.ItemClick {
                override fun onClick(view: View, contact: Contact) {
                    Log.d(TAG_MY_GROUP_ADAPTER, "on click")
                    onStartDetailListener?.onStartDetailFragment(contact)
                }

                override fun onStarClick(view: View, contact:Contact) {
                    Log.d(TAG_MY_GROUP_ADAPTER, "on star click")
                    ContactManager.toggleFavoriteContact(contact)
                    onFavoriteChangeListener?.onFavoriteChanged()
                }
            }
        }
    }

    override fun onAddContact() {
        Log.d(TAG_MY_GROUP_ADAPTER, "on add contact")
        notifyDataSetChanged()
    }

    inner class MyGroupHolder(private val binding: RecyclerViewGroupItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val groupNameTextView = binding.tvGroupName
        val contactCountTextView = binding.tvContactCount
        val groupRecyclerView = binding.recyclerviewGroup
    }
}