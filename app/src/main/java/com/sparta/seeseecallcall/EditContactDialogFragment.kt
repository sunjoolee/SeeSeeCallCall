package com.sparta.seeseecallcall

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.marginBottom
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sparta.seeseecallcall.Constants.TAG_ADD_CONTACT
import com.sparta.seeseecallcall.data.Contact
import com.sparta.seeseecallcall.data.MyContactManager
import com.sparta.seeseecallcall.data.MyContactManager.myContact
import com.sparta.seeseecallcall.databinding.FragmentAddContactDialogBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class EditContactDialogFragment() : DialogFragment() {
    interface OnEditContactListener{
        fun onEditContact()
    }


    private var _binding: FragmentAddContactDialogBinding? = null
    private val binding get() = _binding!!

    var onEditContactListener:OnEditContactListener? = null


    var imageUri:Uri? = null
    private val imageIntentLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            imageUri = it.data?.data
            activity?.grantUriPermission(
                "com.sparta.seeseecallcall",
                imageUri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )

            binding.imgProfile.setImageURI(imageUri)
            binding.imgProfile.clipToOutline = true
        }
    }

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

        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNav?.visibility = View.GONE

        initProfileImageButton()

        initMbtiSpinner()
        initBirthDateDialog()

        initCancelButton()
        initOkayButton()

        initUI() //myContact 정보 UI에 그리기
    }

    private fun initProfileImageButton() {
        binding.imgProfile.setOnClickListener {
            if (imageUri != null) { //이미 프로필 사진이 있는 경우, 프로필 삭제
                imageUri = null

                val mbti = binding.spinnerMbti.selectedItem.toString()
                binding.imgProfile.setImageResource(
                    if (mbti == "????") R.drawable.profile_mbti
                    else resources.getIdentifier(
                        "profile_${mbti.toLowerCase()}",
                        "drawable",
                        "com.sparta.seeseecallcall"
                    )
                )
                binding.imgProfile.clipToOutline = true

                return@setOnClickListener
            }
            //갤러리에서 프로필 사진 가져오기
            val imageIntent = Intent(
                Intent.ACTION_GET_CONTENT,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI
            )
            imageIntentLauncher.launch(imageIntent)
        }
    }

    private fun initMbtiSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_mbti,
            R.layout.spinner_text
        ).also { adapter ->
            binding.spinnerMbti.adapter = adapter
        }

        binding.spinnerMbti.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    adapterView: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (imageUri != null) return

                    binding.imgProfile.setImageResource(
                        if (position == 0) R.drawable.profile_mbti
                        else resources.getIdentifier(
                            "profile_${
                                binding.spinnerMbti.getItemAtPosition(position).toString()
                                    .toLowerCase()
                            }",
                            "drawable",
                            "com.sparta.seeseecallcall"
                        )
                    )
                    binding.imgProfile.clipToOutline = true
                }

                override fun onNothingSelected(adapterView: AdapterView<*>?) {
                    if (imageUri != null) return
                    else binding.imgProfile.setImageResource(R.drawable.profile_mbti)
                    binding.imgProfile.clipToOutline = true
                }
            }
    }
    private fun initBirthDateDialog() {
        binding.tvBirthDate.setOnClickListener {
            val cal = Calendar.getInstance()
            DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    val selectedDate = Calendar.getInstance().apply {
                        set(year, month, day)
                    }
                    val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
                    binding.tvBirthDate.text = dateFormat.format(selectedDate.time)
                },
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).run {
                datePicker.maxDate = Date().time
                show()
            }
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

            val newContact = Contact(
                profileImage =  imageUri,
                name = binding.etName.text.toString(),
                mbti = binding.spinnerMbti.selectedItem.toString(),
                phoneNumber = binding.etPhoneNumber.text.toString(),
                email = binding.etEmail.text.toString(),
                birthDate = binding.tvBirthDate.text.toString()
            )

            MyContactManager.myContact = newContact
            onEditContactListener?.onEditContact()
            dismiss()
        }
    }

    private fun initUI(){
        myContact.run{
            if(profileImage != null){
               binding.imgProfile.setImageURI(profileImage)
               imageUri = profileImage
            }
            else{
                binding.imgProfile.setImageResource(
                    if (mbti == "????") R.drawable.profile_mbti
                    else resources.getIdentifier(
                    "profile_${mbti.toLowerCase(Locale.ROOT)}",
                    "drawable",
                    "com.sparta.seeseecallcall"
                    )
                )
            }
            binding.imgProfile.clipToOutline = true

            val mbtiArray = resources.getStringArray(R.array.spinner_mbti)
            binding.spinnerMbti.setSelection(mbtiArray.indexOf(mbti))

            binding.etName.setText(name)
            binding.etPhoneNumber.setText(phoneNumber)
            binding.etEmail.setText(email)
            binding.tvBirthDate.text = birthDate
        }
    }

    override fun onDestroyView() {
        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNav?.visibility = View.VISIBLE
        super.onDestroyView()
    }

}
