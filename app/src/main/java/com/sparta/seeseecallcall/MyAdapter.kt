package com.sparta.seeseecallcall

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sparta.seeseecallcall.data.Contact
import com.sparta.seeseecallcall.databinding.RecyclerViewItemBinding



class MyAdapter(private val dataset: MutableList<Contact>) :
    RecyclerView.Adapter<MyAdapter.MyHolder>() {
    interface ItemClick {
        fun onClick(view: View, position: Int)
        fun onStarClick(view:View, position:Int)
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


        val contact: Contact = dataset[position]
        holder.starImageView.setImageResource(
            if (contact.favorite) R.drawable.icon_star_full
            else R.drawable.icon_star_empty
        )
        holder.profileImageView.run{
            if(contact.profileImage == null){
                setImageResource(R.drawable.icon_profile_default)
            }
            else{
                setImageURI(contact.profileImage)
                clipToOutline = true
            }
        }
        holder.nameTextView.text = contact.name
    }

    inner class MyHolder(private val binding: RecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val starImageView: ImageView = binding.imgStar
        val profileImageView: ImageView = binding.imgProfile
        val nameTextView: TextView = binding.tvName
    }
}