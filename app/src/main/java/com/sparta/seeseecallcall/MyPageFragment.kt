package com.sparta.seeseecallcall

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.sparta.seeseecallcall.data.Contact
import com.sparta.seeseecallcall.data.MyContactManager
import com.sparta.seeseecallcall.data.MyContactManager.myContact
import com.sparta.seeseecallcall.databinding.FragmentMyPageBinding
import java.util.Locale


class MyPageFragment : Fragment(), EditContactDialogFragment.OnEditContactListener {
    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyPageBinding.inflate(layoutInflater, container, false)

        drawUI(myContact)

        val mbtitestBtn: ConstraintLayout? = binding?.lyMbtitest
        mbtitestBtn?.setOnClickListener {
            var intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.16personalities.com/ko/무료-성격-유형-검사")
            )
            startActivity(intent)
        }
        binding?.lyMypageinfor?.setOnClickListener {
            showEditDialog()
        }

        return binding?.root
    }

    private fun showEditDialog() {
        val mypageInforBtn: ConstraintLayout? = binding?.lyMypageinfor
        mypageInforBtn?.setOnClickListener {
            val editContactFragment = EditContactDialogFragment()
            editContactFragment.onEditContactListener = this@MyPageFragment
            requireActivity().supportFragmentManager
                .beginTransaction()
                .add(R.id.nav_host_fragment, editContactFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onEditContact() {
        Log.d("TAG_MY_PAGE", "${MyContactManager.myContact.toString()}")
        drawUI(myContact)
    }

    private fun drawUI(myData: Contact){
        binding?.imgMypageprofil?.run{
            if(myData.profileImage != null)
                setImageURI(myData.profileImage)
            else
                setImageResource(
                    if (myData.mbti == "????") R.drawable.profile_mbti
                    else resources.getIdentifier(
                        "profile_${myData.mbti.toLowerCase(Locale.ROOT)}",
                        "drawable",
                        "com.sparta.seeseecallcall"
                    )
                )
            clipToOutline = true
        }
        binding?.tvMypageMBTI?.text = myData.mbti
        binding?.tvMypageName?.text = myData.name
        binding?.tvMypagePhon?.text = myData.phoneNumber
        binding?.tvMypageEmail?.text = myData.email
        binding?.tvMypageBirth?.text = myData.birthDate
    }
}