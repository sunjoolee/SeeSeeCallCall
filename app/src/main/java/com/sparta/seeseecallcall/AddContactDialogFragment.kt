package com.sparta.seeseecallcall

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.sparta.seeseecallcall.Constants.TAG_ADD_CONTACT
import com.sparta.seeseecallcall.data.Contact
import com.sparta.seeseecallcall.data.ContactManager
import com.sparta.seeseecallcall.databinding.FragmentAddContactDialogBinding
import java.lang.Exception

class AddContactDialogFragment() : DialogFragment() {

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

    private fun initProfileImageButton() {
        binding.imgProfile.setOnClickListener {
            //TODO 갤러리에서 사진 가져오기
        }
    }

    private fun initMbtiSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_mbti,
            R.layout.spinner_text
        ).also { adapter ->
            //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerMbti.adapter = adapter
        }
    }

        private fun initCancelButton() {
        binding.btnCancel.setOnClickListener {
            Log.d(TAG_ADD_CONTACT, "cancel button clicked")
            dismiss()
        }
    }

    enum class ErrorMsg(val id: Int) {
        PASS(R.string.pass),
        NAME_EMPTY(R.string.error_no_name),
        PHONE_NUMBER_EMPTY(R.string.error_no_phone_number),
        PHONE_NUMBER_WRONG(R.string.error_wrong_phone_number),
        EMAIL_EMPTY(R.string.error_no_email),
        EMAIL_WRONG(R.string.error_wrong_email)
    }

    private fun getNameError(text: String): ErrorMsg = (
            if (text.isNullOrBlank()) ErrorMsg.NAME_EMPTY
            else ErrorMsg.PASS
            )

    private fun getPhoneNumberError(text: String): ErrorMsg = (
            if (text.isNullOrBlank()) ErrorMsg.PHONE_NUMBER_EMPTY
            else if (!text.matches("""^\d{3}-\d{3,4}-\d{4}$""".toRegex())) ErrorMsg.PHONE_NUMBER_WRONG
            else ErrorMsg.PASS
            )

    private fun getEmailError(text: String): ErrorMsg = (
            if (text.isNullOrBlank()) ErrorMsg.EMAIL_EMPTY
            else if (!text.matches("""^[A-z0-9]{2,10}+@[A-z]{2,20}+.[a-z]{2,3}$""".toRegex())) ErrorMsg.EMAIL_WRONG
            else ErrorMsg.PASS
            )

    private fun initOkayButton() {
        binding.btnOk.setOnClickListener {
            Log.d(TAG_ADD_CONTACT, "ok button clicked")

            //유효성 검사
           with(getNameError(binding.etName.text.toString())){
               if (this != ErrorMsg.PASS) {
                   binding.tvError.setText(this.id)
                   return@setOnClickListener
               }
           }
            with(getPhoneNumberError(binding.etPhoneNumber.text.toString())){
                if (this != ErrorMsg.PASS) {
                    binding.tvError.setText(this.id)
                    return@setOnClickListener
                }
            }
            with(getEmailError(binding.etEmail.text.toString())){
                if (this != ErrorMsg.PASS) {
                    binding.tvError.setText(this.id)
                    return@setOnClickListener
                }
            }

            ContactManager.addNewContact(
                profileImage = null, //TODO 프로필 사진 등록
                name = binding.etName.text.toString(),
                mbti = binding.spinnerMbti.selectedItem.toString(),
                phoneNumber = binding.etPhoneNumber.text.toString(),
                email = binding.etEmail.text.toString(),
                birthDate = "0000/00/00" //TODO 생일 등록
            )
            dismiss()
        }
    }



}
