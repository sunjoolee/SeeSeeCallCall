package com.sparta.seeseecallcall

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sparta.seeseecallcall.data.Contact
import com.sparta.seeseecallcall.data.ContactManager
import com.sparta.seeseecallcall.data.MbtiManager
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

        binding.btnStar.setOnClickListener {
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

        binding.btnDelete.setOnClickListener {
            showDeleteDialog()
        }

        binding.btnGunghab.setOnClickListener{
            Log.d("보내는 mbti", contactData?.mbti.toString())
            showMbtiDialog(contactData?.mbti.toString())
        }
    }

    private fun showDeleteDialog() {
        val deleteDialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_delete, null)

        val dialog = Dialog(requireContext())
        dialog.setContentView(deleteDialogView)
        dialog.setContentView(R.layout.dialog_delete)

        val closeBtn = deleteDialogView.findViewById<Button>(R.id.btn_close)
        val confirmBtn = deleteDialogView.findViewById<Button>(R.id.btn_confirm)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        closeBtn.setOnClickListener {
            dialog.dismiss()
        }
        confirmBtn.setOnClickListener {

        }

        dialog.show()
    }

    private fun showMbtiDialog(mbti: String) {
        val mbtiDialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_mbti, null)
        val typeImage = mbtiDialogView.findViewById<ImageView>(R.id.iv_type)
        val type = mbtiDialogView.findViewById<TextView>(R.id.tv_mbti)
        val shortDesc = mbtiDialogView.findViewById<TextView>(R.id.tv_short_desc)
        val longDesc = mbtiDialogView.findViewById<TextView>(R.id.tv_long_desc)
//        val bestComp = mbtiDialogView.findViewById<TextView>(R.id.tv_mbti)
//        val goodComp = mbtiDialogView.findViewById<TextView>(R.id.tv_mbti)
//        val badComp = mbtiDialogView.findViewById<TextView>(R.id.tv_mbti)
        //이미지도 필요

        typeImage.clipToOutline = true



        val dialog = Dialog(requireContext())
        dialog.setContentView(mbtiDialogView)
//        dialog.setContentView(R.layout.dialog_mbti)

        val closeBtn = mbtiDialogView.findViewById<Button>(R.id.btn_close)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        Log.d("받는 mbti", mbti)
        val mbti = MbtiManager.mbtiList.find { it.type == mbti }
        mbti?.let{
            type.text = mbti.type
            shortDesc.text = mbti.short_description
            longDesc.text = mbti.long_description
            if (mbti.type == "????")
                typeImage.setImageResource(R.drawable.profile_mbti)
            else
                typeImage.setImageResource(
                    resources.getIdentifier(
                        "profile_${mbti.toString().toLowerCase()}",
                        "drawable",
                        "com.sparta.seeseecallcall"
                    )
                )
        }
        closeBtn.setOnClickListener {
            dialog.dismiss()
        }

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