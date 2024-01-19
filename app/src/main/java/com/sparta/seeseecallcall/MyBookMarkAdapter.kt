package com.sparta.seeseecallcall

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sparta.seeseecallcall.data.CompatibilityColor
import com.sparta.seeseecallcall.data.Contact
import com.sparta.seeseecallcall.data.MbtiManager
import com.sparta.seeseecallcall.databinding.BookmarkRecyclerViewItemBinding

class MyBookMarkAdapter(private var dataset: MutableList<Contact>) :
    RecyclerView.Adapter<MyBookMarkAdapter.MyViewHolder>() {

    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = BookmarkRecyclerViewItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(holder.itemView, dataset[holder.adapterPosition])
        }
        holder.starImageView.setOnClickListener {
            itemClick?.onStarClick(holder.itemView, dataset[holder.adapterPosition])
        }

        bind(holder, position)
    }

    fun changeDataset(newDataset: MutableList<Contact>) {
        dataset = newDataset
        notifyDataSetChanged()
    }


    private fun bind(holder: MyViewHolder, position: Int) {
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
                        itemView.resources.getIdentifier(
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
                ContextCompat.getColor(
                    holder.itemView.context,
                    if (contact.mbti == "????") {
                        CompatibilityColor.UN_KNOWN.color
                    } else {
                        MbtiManager.getCompatibilityColor(contact.mbti)
                    }
                )
            )
        }
    }

    inner class MyViewHolder(private val binding: BookmarkRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val starImageView = binding.imgStar
        val profileImageView = binding.imgProfile
        val nameTextView = binding.tvName
        val mbtiTextView = binding.tvMbti
    }
}
