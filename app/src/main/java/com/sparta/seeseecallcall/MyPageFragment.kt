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
import com.sparta.seeseecallcall.data.MyContactManager
import com.sparta.seeseecallcall.databinding.FragmentMyPageBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyPageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_my_page, container, false)
        _binding = FragmentMyPageBinding.inflate(layoutInflater, container, false)
        val mydata = MyContactManager.myContact
        Log.d("MyPageFragment", "mydata = ${mydata.name}")
        binding?.tvMypageMBTI?.text = mydata.mbti
        binding?.tvMypageBirth?.text = mydata.birthDate
        binding?.tvMypageEmail?.text = mydata.email
        binding?.tvMypageName?.text = mydata.name
        binding?.tvMypagePhon?.text = mydata.phoneNumber


        val mbtitestBtn: ConstraintLayout? = binding?.lyMbtitest
        mbtitestBtn?.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.16personalities.com/ko/무료-성격-유형-검사"))
            startActivity(intent)
        }

        binding?.lyMypageinfor?.setOnClickListener {
            showEditDialog()
        }


        return binding?.root

    }

    private fun showEditDialog() {
        val MypageInforBtn: ConstraintLayout? = binding?.lyMypageinfor
        MypageInforBtn?.setOnClickListener {
//            val builder = AlertDialog.Builder(requireContext())
//
//            val myPageInfor = layoutInflater.inflate(R.layout.fragment_add_contact_dialog, null)
//            builder.setView(myPageInfor)
//
//            val listener = DialogInterface.OnClickListener{p0, p1 ->
//                val alert = p0 as AlertDialog
////                val mbtispinner: Spinner? = alert.findViewById<Spinner>(R.id.spinner_mbti)
//                val nameinfor: EditText? = alert.findViewById<EditText>(R.id.et_name)
//                val phonNumberInfor: EditText? = alert.findViewById<EditText>(R.id.et_phone_number)
//                val emailInfor: EditText? = alert.findViewById<EditText>(R.id.et_email)
//
//                //binding.tvMypageMBTI.text = "${mbtispinner?.}"
//                binding?.tvMypageName?.text = "${nameinfor?.text}"
//                binding?.tvMypagePhon?.text = "${phonNumberInfor?.text}"
//                binding?.tvMypageEmail?.text = "${emailInfor?.text}"
//
//            }
//
//            builder.setPositiveButton("",listener)
//
//            builder.show()

            val addContactFragment = AddContactDialogFragmentMypage()
            //addContactFragment.addContactListner = this@ContactListFragment
            requireActivity().supportFragmentManager
                .beginTransaction()
                .add(R.id.nav_host_fragment,addContactFragment)
                .addToBackStack(null)
                .commit()
        }

//        binding?.lyMypageinfor?.setOnClickListener {
////            AddContactDialogFragment()
////        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MyPageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyPageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}