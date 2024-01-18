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
import com.sparta.seeseecallcall.data.ContactBookmarkManager
import com.sparta.seeseecallcall.data.ContactGroup
import com.sparta.seeseecallcall.databinding.RecyclerViewGroupItemBinding
interface OnStartDetailListener {
    fun onStartDetail(contact: Contact)
}
class MyGroupAdapter(private var groupDataset: MutableList<ContactGroup>) :
    RecyclerView.Adapter<MyGroupAdapter.MyGroupHolder>(),
    OnAddContactListener {

    var onStartDetailListener: OnStartDetailListener? = null
    var onFavoriteChangeListener: OnFavoriteChangeListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyGroupHolder {
        val binding = RecyclerViewGroupItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MyGroupHolder(binding)
    }

    override fun getItemCount(): Int = groupDataset.size


    override fun onBindViewHolder(holder: MyGroupHolder, position: Int) {
        holder.run {
            if(groupDataset[position].contactList.isEmpty()) {
                itemView.visibility = View.GONE
                itemView.layoutParams = RecyclerView.LayoutParams(0, 0)
            }
            else{
                itemView.visibility = View.VISIBLE
                itemView.layoutParams = RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }

            groupNameTextView.text = groupDataset[position].groupName
            contactCountTextView.text = groupDataset[position].contactList.size.toString() + "명"

            val adapter = MyAdapter(groupDataset[position].contactList)
            groupRecyclerView.adapter = adapter
            groupRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
            groupRecyclerView.addItemDecoration(
                DividerItemDecoration(
                    itemView.context,
                    LinearLayout.VERTICAL
                )
            )

            adapter.itemClick = object : ItemClick {
                override fun onClick(view: View, contact: Contact) {
                    Log.d(TAG_MY_GROUP_ADAPTER, "on click")
                    onStartDetailListener?.onStartDetail(contact)
                }

                override fun onStarClick(view: View, contact: Contact) {
                    Log.d(TAG_MY_GROUP_ADAPTER, "on star click")
                    ContactBookmarkManager.toggleFavoriteContact(contact)
                    onFavoriteChangeListener?.onFavoriteChanged()
                }
            }
        }
    }

    fun changeDataset(newGroupDataset: MutableList<ContactGroup>) {
        groupDataset = newGroupDataset
        notifyDataSetChanged()
    }

    override fun onAddContact() {
        Log.d(TAG_MY_GROUP_ADAPTER, "on add contact")
        notifyDataSetChanged()
    }

    inner class MyGroupHolder(private val binding: RecyclerViewGroupItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val groupLayout = binding.layoutGroup
        val groupNameTextView = binding.tvGroupName
        val contactCountTextView = binding.tvContactCount
        val groupRecyclerView = binding.recyclerviewGroup
    }
}