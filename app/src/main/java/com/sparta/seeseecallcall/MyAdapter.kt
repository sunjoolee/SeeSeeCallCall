package com.sparta.seeseecallcall

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.sparta.seeseecallcall.Constants.TAG_MY_ADAPTER
import com.sparta.seeseecallcall.data.Contact
import com.sparta.seeseecallcall.data.MbtiManager
import com.sparta.seeseecallcall.databinding.RecyclerViewItemBinding


class MyAdapter(private var dataset: MutableList<Contact>) :
    RecyclerView.Adapter<MyAdapter.MyHolder>() {

    interface ItemClick {
        fun onClick(view: View, contact:Contact)
        fun onStarClick(view: View,contact: Contact)
    }

    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = RecyclerViewItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MyHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.itemView.setOnClickListener {
            Log.d(TAG_MY_ADAPTER,"on click")
            itemClick?.onClick(holder.itemView, dataset[holder.adapterPosition])
        }
        holder.starImageView.setOnClickListener {
            Log.d(TAG_MY_ADAPTER,"on star click")
            itemClick?.onStarClick(holder.itemView, dataset[holder.adapterPosition])
        }

        bind(holder, position)
    }

    fun changeDataset(newDataset:MutableList<Contact>){
        dataset = newDataset
        notifyDataSetChanged()
    }


    inner class MyHolder(private val binding: RecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val starImageView: ImageView = binding.imgStar
        val profileImageView: ImageView = binding.imgProfile
        val nameTextView: TextView = binding.tvName
        val mbtiTextView: TextView = binding.tvMbti
        val phoneNumberTextView: TextView = binding.tvPhoneNumber
    }

    private fun bind(holder: MyHolder, position: Int) {
        val contact: Contact = dataset[position]

        holder.run {
            starImageView.setImageResource(
                if (contact.favorite) R.drawable.icon_star_yellow
                else R.drawable.icon_star_gray
            )
            profileImageView.run {
                if (contact.profileImage != null)
                    setImageURI(contact.profileImage)
                else if (contact.mbti == "????")
                    setImageResource(R.drawable.profile_mbti)
                else
                    setImageResource(
                        resources.getIdentifier(
                            "profile_${contact.mbti.toLowerCase()}",
                            "drawable",
                            "com.sparta.seeseecallcall"
                        )
                    )

                clipToOutline = true
            }
            nameTextView.text = contact.name
            mbtiTextView.text = contact.mbti
            mbtiTextView.background.setTint(
                getColor(
                    holder.itemView.context,
                    MbtiManager.getCompatibilityColor(contact.mbti)
                )
            )
            phoneNumberTextView.text = contact.phoneNumber
        }
    }
}