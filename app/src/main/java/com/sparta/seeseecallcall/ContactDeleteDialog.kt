package com.sparta.seeseecallcall

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import com.sparta.seeseecallcall.databinding.DialogDeleteBinding

class ContactDeleteDialog(private val context:AppCompatActivity) {

    interface OnConfirmButtonClickedListener{
        fun onConfirmButtonClicked()
    }

    private lateinit var binding: DialogDeleteBinding
    private val dialog = Dialog(context)

    var onConfirmButtonClickedListener:OnConfirmButtonClickedListener? = null

    fun show(){
        binding = DialogDeleteBinding.inflate(context.layoutInflater)

        dialog.setContentView(binding.root)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val closeBtn = binding.btnClose
        val confirmBtn = binding.btnConfirm

        closeBtn.setOnClickListener {
            dialog.dismiss()
        }
        confirmBtn.setOnClickListener {
            onConfirmButtonClickedListener?.onConfirmButtonClicked()
            dialog.dismiss()
        }

        dialog.show()
    }
}