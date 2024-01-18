package com.sparta.seeseecallcall

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.sparta.seeseecallcall.data.CompatibilityColor
import com.sparta.seeseecallcall.data.Contact
import com.sparta.seeseecallcall.data.ContactManager
import com.sparta.seeseecallcall.data.MbtiManager
import com.sparta.seeseecallcall.databinding.RecyclerViewItemBinding


class MyAdapter(private var dataset: MutableList<Contact>) :
    RecyclerView.Adapter<MyAdapter.MyHolder>() {
    private val TAG = "MyAdapter"

    interface ItemClick {
        fun onClick(view: View, position: Int)
        fun onStarClick(view: View, position: Int)
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
            itemClick?.onClick(holder.itemView, holder.adapterPosition)
        }
        holder.starImageView.setOnClickListener {
            itemClick?.onStarClick(holder.itemView, holder.adapterPosition)
        }

        bind(holder, position)
    }

    fun ChangeDataset(newDataset:MutableList<Contact>){
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
                    if (contact.mbti == "????") {
                        CompatibilityColor.UN_KNOWN.color
                    } else {
                        val contactId: Int = MbtiManager.mbtiId[contact.mbti] ?: 0
                        val myId: Int = MbtiManager.mbtiId[ContactManager.myContact.mbti] ?: 0

                        MbtiManager.compatibilityColor[contactId][myId].color
                    }
                )
            )

            phoneNumberTextView.text = contact.phoneNumber
        }
    }
}