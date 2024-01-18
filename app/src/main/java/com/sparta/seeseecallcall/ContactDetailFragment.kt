package com.sparta.seeseecallcall

import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sparta.seeseecallcall.data.Contact
import com.sparta.seeseecallcall.data.ContactManager
import com.sparta.seeseecallcall.databinding.FragmentContactDetailBinding

interface OnFavoriteChangeListener {
    fun onFavoriteChanged(contact: Contact)
}

class ContactDetailFragment : Fragment() {

    private lateinit var binding: FragmentContactDetailBinding
    private var contactData: Contact? = null
    var listener: OnFavoriteChangeListener? = null

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

        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNav?.visibility = View.GONE

        arguments?.let {
            contactData = it.getParcelable(Constants.ARG_CONTACT)
        }

        contactData?.let { data ->
            binding.imgDetailprofil.run {
                if (data.profileImage != null) setImageURI(data.profileImage)
                else setImageResource(
                    if (data.mbti == "????") R.drawable.profile_mbti
                    else resources.getIdentifier(
                        "profile_${data.mbti.toLowerCase()}",
                        "drawable",
                        "com.sparta.seeseecallcall"
                    )
                )
            }
            binding.tvDetailname.text = data.name
            binding.tvDetailMBTI.text = data.mbti
            binding.tvDetailPhon.text = data.phoneNumber
            binding.tvDetailEmail.text = data.email
            binding.tvDetailBirth.text = data.birthDate
            if (contactData?.favorite == true)
                binding.imgStar.setImageResource(R.drawable.icon_star_yellow)
            else
                binding.imgStar.setImageResource(R.drawable.icon_star_gray)
        }

        binding.lyStar.setOnClickListener {
            contactData?.favorite = contactData?.favorite != true
            Log.d(Constants.TAG, contactData?.favorite.toString())
            if (contactData?.favorite == true) {
                binding.imgStar.setImageResource(R.drawable.icon_star_yellow)
                ContactManager.contactBookmarkList.add(contactData!!)
            } else {
                binding.imgStar.setImageResource(R.drawable.icon_star_gray)
                ContactManager.contactBookmarkList.remove(contactData!!)
            }
            listener?.onFavoriteChanged(contactData!!)
        }

        Log.d("받는 Detail 프래그먼트", contactData?.phoneNumber.toString())

        binding.imgBackBtn.setOnClickListener {
            requireFragmentManager().popBackStack()
        }

        binding.lyDelete.setOnClickListener {
            showDeleteDialog()
        }

        //메세지 보내기
        binding.lyChat.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            val phonnumber = contactData?.phoneNumber
            intent.data = Uri.parse("smsto:${phonnumber}")
            startActivity(intent)
        }
        //전화걸기
        binding.lyCall.setOnClickListener {
            val phonnumber = contactData?.phoneNumber
            val intent = Intent(Intent.ACTION_CALL, Uri.parse(phonnumber))
            startActivity(intent)
        }
    }

    private fun showDeleteDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_mbti, null)

        val dialog = Dialog(requireContext())
        dialog.setContentView(dialogView)
        dialog.setContentView(R.layout.dialog_mbti)
        dialog.setCanceledOnTouchOutside(false)

        val closeBtn = dialogView.findViewById<Button>(R.id.btn_close)
//        val confirmBtn = dialogView.findViewById<Button>(R.id.btn_confirm)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        closeBtn.setOnClickListener {
            dialog.dismiss()
        }
//        confirmBtn.setOnClickListener {
//
//        }

        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNav?.visibility = View.VISIBLE
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