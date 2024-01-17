package com.sparta.seeseecallcall

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.sparta.seeseecallcall.Constants.TAG_ADD_CONTACT
import com.sparta.seeseecallcall.databinding.FragmentAddContactDialogBinding
import com.sparta.seeseecallcall.databinding.FragmentContactListBinding

class AddContactDialogFragment() : DialogFragment (){

    private var _binding: FragmentAddContactDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddContactDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initProfileImageButton()

        initMbtiSpinner()

        initCancelButton()
        initOkayButton()
    }

    private fun initProfileImageButton(){
        binding.imgProfile.setOnClickListener {
            //TODO 갤러리에서 사진 가져오기
        }
    }

    private fun initMbtiSpinner(){
        //TODO MBTI 선택 스피너 구현하기
    }

    private fun initCancelButton(){
        binding.btnCancel.setOnClickListener {
            Log.d(TAG_ADD_CONTACT,"cancel button clicked")
            dismiss()
        }
    }
    private fun initOkayButton(){
        binding.btnOk.background.setTint(resources.getColor(R.color.gray))

        binding.btnOk.setOnClickListener {
            Log.d(TAG_ADD_CONTACT,"ok button clicked")

            if(!isNameValid(binding.etName.text.toString())){

                return@setOnClickListener
            }

            //TODO 입력된 연락처 정보 저장
            dismiss()
        }
    }

    private fun isNameValid(text:String):Boolean{
        return !(text.isNullOrBlank())
    }
    private fun isPhoneNumberValid(text:String):Boolean{
        return text.matches("""^\d{3}-\d{3,4}-\d{4}$""".toRegex())
    }

    private fun isEmailValid(text:String):Boolean{
        return text.matches("""^[A-z0-9]{2,10}+@[A-z]{2,20}+.[a-z]{2,3}$""".toRegex())
    }
}