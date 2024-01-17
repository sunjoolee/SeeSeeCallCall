package com.sparta.seeseecallcall

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sparta.seeseecallcall.data.Contact
import com.sparta.seeseecallcall.databinding.FragmentContactDetailBinding

class ContactDetailFragment : Fragment() {

    private lateinit var binding: FragmentContactDetailBinding
    private var contactData: Contact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            contactData = it.getParcelable(Constants.ARG_CONTACT)
        }
        Log.d("받는 Detail 프래그먼트", contactData?.phoneNumber.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            contactData = it.getParcelable(Constants.ARG_CONTACT)
        }

        contactData?.let { data ->
//             binding.imgDetailprofil.setImageResource(data.profileImage)
            binding.tvDetailname.text = data.name
            binding.tvDetailMBTI.text = data.mbti
            binding.tvDetailPhon.text = data.phoneNumber
            binding.tvDetailEmail.text = data.email
            binding.tvDetailBirth.text = data.birthDate
        }

        Log.d("받는 Detail 프래그먼트", contactData?.phoneNumber.toString())
    }

    companion object {
        @JvmStatic
        fun newInstance(contact: Contact) =
            ContactDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(Constants.ARG_CONTACT, contact)
                }
            }
    }
}