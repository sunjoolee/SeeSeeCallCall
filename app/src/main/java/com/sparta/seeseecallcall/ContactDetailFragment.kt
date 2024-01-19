package com.sparta.seeseecallcall

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sparta.seeseecallcall.Constants.TAG_DETAIL
import com.sparta.seeseecallcall.data.CompatibilityText
import com.sparta.seeseecallcall.data.Contact
import com.sparta.seeseecallcall.data.ContactBookmarkManager
import com.sparta.seeseecallcall.data.ContactGroupManager
import com.sparta.seeseecallcall.data.MbtiManager
import com.sparta.seeseecallcall.databinding.FragmentContactDetailBinding

interface OnFavoriteChangeListener {
    fun onFavoriteChanged()
}

interface OnContactDeleteListener {
    fun onContactDelete()
}

class ContactDetailFragment : Fragment(), ContactDeleteDialog.OnConfirmButtonClickedListener {

    private lateinit var binding: FragmentContactDetailBinding
    private var contactData: Contact? = null
    var onFavoriteChangeListener: OnFavoriteChangeListener? = null
    var onContactDeleteListener: OnContactDeleteListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            contactData = it.getParcelable(Constants.ARG_CONTACT)
        }
        Log.d("받는 Detail 프래그먼트", contactData.toString())
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
        Log.d("받는 Detail 프래그먼트2", contactData?.mbti.toString())
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

            binding.tvDetailMBTI.background.setTint(
                ContextCompat.getColor(
                    requireContext(),
                    MbtiManager.getCompatibilityColor(data.mbti)
                )
            )

            Log.d("mbti1",data.mbti)
            if (contactData?.favorite == true)
                binding.imgStar.setImageResource(R.drawable.icon_star_yellow)
            else
                binding.imgStar.setImageResource(R.drawable.icon_star_gray)
        }

        binding.btnStar.setOnClickListener {
            ContactBookmarkManager.toggleFavoriteContact(contactData!!)
            binding.imgStar.setImageResource(
                if (contactData!!.favorite) R.drawable.icon_star_yellow
                else R.drawable.icon_star_gray
            )
            onFavoriteChangeListener?.onFavoriteChanged()
        }

        binding.imgBackBtn.setOnClickListener {
            requireFragmentManager().popBackStack()
        }

        binding.btnDelete.setOnClickListener {
            showDeleteDialog()
        }

        binding.btnGunghab.setOnClickListener {
            Log.d("보내는 mbti", contactData?.mbti.toString())
            if (contactData?.mbti == "????")
                showMysteryMbtiDialog()
            else
                showMbtiDialog(contactData?.mbti.toString())
        }

        //메세지 보내기
        binding.lyChat.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("smsto:${contactData?.phoneNumber}")
            startActivity(intent)
        }
        //전화걸기
        binding.lyCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse(contactData?.phoneNumber))
            startActivity(intent)
        }
    }

    private fun showDeleteDialog() {
        Log.d(TAG_DETAIL, "show delete dialog")
        val deleteDialog = ContactDeleteDialog(binding.root.context as AppCompatActivity)
        deleteDialog.onConfirmButtonClickedListener = this@ContactDetailFragment
        deleteDialog.show()
    }

    override fun onConfirmButtonClicked() {
        ContactBookmarkManager.deleteContactFromBookmark(contactData!!)
        ContactGroupManager.deleteContactFromGroup(contactData!!)
        onContactDeleteListener?.onContactDelete()

        activity?.supportFragmentManager?.popBackStack()
    }


    private fun showMbtiDialog(mbti: String) {
        val mbtiDialogView =
            LayoutInflater.from(requireContext()).inflate(R.layout.dialog_mbti, null)
        val typeImage = mbtiDialogView.findViewById<ImageView>(R.id.iv_type)
        val type = mbtiDialogView.findViewById<TextView>(R.id.tv_mbti)
        val shortDesc = mbtiDialogView.findViewById<TextView>(R.id.tv_short_desc)
        val longDesc = mbtiDialogView.findViewById<TextView>(R.id.tv_long_desc)
        val compability = mbtiDialogView.findViewById<TextView>(R.id.tv_compability)
        val lyBestComp = mbtiDialogView.findViewById<HorizontalScrollView>(R.id.ly_best_scroll)
        val lyGoodComp = mbtiDialogView.findViewById<HorizontalScrollView>(R.id.ly_good_scroll)
        val lyBadComp = mbtiDialogView.findViewById<HorizontalScrollView>(R.id.ly_bad_scroll)
        val bestComp = mbtiDialogView.findViewById<LinearLayout>(R.id.ly_best)
        val goodComp = mbtiDialogView.findViewById<LinearLayout>(R.id.ly_good)
        val badComp = mbtiDialogView.findViewById<LinearLayout>(R.id.ly_bad)
        val tvBest = mbtiDialogView.findViewById<TextView>(R.id.tv_best)
        val tvGood = mbtiDialogView.findViewById<TextView>(R.id.tv_good)
        val tvBad = mbtiDialogView.findViewById<TextView>(R.id.tv_bad)


        val dialog = Dialog(requireContext())
        dialog.setContentView(mbtiDialogView)

        val closeBtn = mbtiDialogView.findViewById<Button>(R.id.btn_close)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        Log.d("받는 mbti", mbti)
        val dialogMbti = MbtiManager.mbtiList.find { it.type == mbti }
        Log.d("변환한 mbti", dialogMbti.toString())
        dialogMbti?.let { mbti ->
            type.text = mbti.type
            shortDesc.text = mbti.short_description
            longDesc.text = mbti.long_description

            typeImage.setImageResource(
                resources.getIdentifier(
                    "profile_${mbti.type.toLowerCase()}",
                    "drawable",
                    "com.sparta.seeseecallcall"
                )
            )
            typeImage.clipToOutline = true

            val compatibility = MbtiManager.getCompatibility(mbti.type)
            val compatibilityText = getString(compatibility.textId)

            applyColorToCompatibilityText(compability, compatibilityText)

            // 최고 궁합 처리
            if (mbti.bestCompatibility.isEmpty()) {
                tvBest.visibility = View.GONE
                lyBestComp.visibility = View.GONE
            } else {
                mbti.bestCompatibility.forEach { compatibility ->
                    val textView = createCompatibilityTextView(compatibility, R.color.mbti_best)
                    bestComp.addView(textView)
                }
            }

            // 좋은 궁합 처리
            if (mbti.goodCompatibility.isEmpty()) {
                tvGood.visibility = View.GONE
                lyGoodComp.visibility = View.GONE
            } else {
                mbti.goodCompatibility.forEach { compatibility ->
                    val textView = createCompatibilityTextView(compatibility, R.color.mbti_good)
                    goodComp.addView(textView)
                }
            }

            // 나쁜 궁합 처리
            if (mbti.badCompatibility.isEmpty()) {
                tvBad.visibility = View.GONE
                lyBadComp.visibility = View.GONE
            } else {
                mbti.badCompatibility.forEach { compatibility ->
                    val textView = createCompatibilityTextView(compatibility, R.color.mbti_bad)
                    badComp.addView(textView)
                }
            }
        }
        closeBtn.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    fun Int.dpToPx(context: Context): Int {
        return (this * context.resources.displayMetrics.density).toInt()
    }

    // TextView 생성 및 설정을 위한 함수
    private fun createCompatibilityTextView(compatibility: String, colorResId: Int): TextView {
        return TextView(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                65.dpToPx(context), // 여기서 너비를 70dp로 고정
                30.dpToPx(context)  // 여기서 높이를 30dp로 고정
            ).apply {
                marginEnd = 10.dpToPx(context) // 10dp를 픽셀로 변환
            }
            setBackgroundResource(R.drawable.rounded_shape_10)
            backgroundTintList = ContextCompat.getColorStateList(context, colorResId)
            setPadding(20, 10, 20, 10)
            gravity = Gravity.CENTER
            text = compatibility
            textSize = 18f
            setTextColor(ContextCompat.getColor(context, R.color.white))
        }
    }

    private fun showMysteryMbtiDialog() {
        val mysteryDialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_mbti_default, null)
        val compability = mysteryDialogView.findViewById<TextView>(R.id.tv_default_compability)
        val typeImage = mysteryDialogView.findViewById<ImageView>(R.id.iv_type)
        typeImage.setImageResource(R.drawable.profile_mbti)
        typeImage.clipToOutline = true

        val dialog = Dialog(requireContext())
        dialog.setContentView(mysteryDialogView)

        val closeBtn = mysteryDialogView.findViewById<Button>(R.id.btn_close)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val compatibility = MbtiManager.getCompatibility("????")
        val compatibilityText = getString(compatibility.textId)
        compability.text = getString(R.string.mbti_compatibility, compatibilityText)
        applyColorToCompatibilityText(compability, compatibilityText)

        closeBtn.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun applyColorToCompatibilityText(textView: TextView, compatibilityText: String) {
        val formattedText = getString(R.string.mbti_compatibility, compatibilityText)
        val spannableString = SpannableString(formattedText)
        val colorResId = when (compatibilityText) {
            "천생연분" -> R.color.mbti_best
            "좋음" -> R.color.mbti_good
            "무난" -> R.color.mbti_soso
            "최악" -> R.color.mbti_bad
            else -> R.color.gray
        }
        val color = ContextCompat.getColor(requireContext(), colorResId)

        // 궁합 텍스트에만 색상 적용
        val startIndex = formattedText.indexOf(compatibilityText)
        if (startIndex >= 0) {
            spannableString.setSpan(
                ForegroundColorSpan(color),
                startIndex,
                startIndex + compatibilityText.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        // TextView에 SpannableString 설정
        textView.text = spannableString
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