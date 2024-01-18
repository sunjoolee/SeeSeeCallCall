package com.sparta.seeseecallcall

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sparta.seeseecallcall.Constants.TAG_LIST
import com.sparta.seeseecallcall.data.Contact
import com.sparta.seeseecallcall.data.ContactBookmarkManager
import com.sparta.seeseecallcall.data.ContactBookmarkManager.contactBookmarkList
import com.sparta.seeseecallcall.data.ContactGroupManager
import com.sparta.seeseecallcall.data.ContactGroupManager.contactGroupList
import com.sparta.seeseecallcall.databinding.FragmentContactListBinding

class ContactListFragment : Fragment(),
    MyGroupAdapter.OnStartDetailFragment,
    OnFavoriteChangeListener{

    private var _binding: FragmentContactListBinding? = null
    private val binding get() = _binding!!

    private val favoriteAdapter by lazy { MyAdapter(contactBookmarkList) }
    private val groupAdapter by lazy { MyGroupAdapter(contactGroupList) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactListBinding.inflate(inflater, container, false)

        //즐겨찾기 리싸이클러뷰 설정
        binding.recyclerviewFavorite.run{
            adapter = favoriteAdapter
            layoutManager= LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        }
        favoriteAdapter.itemClick = object :MyAdapter.ItemClick{
            override fun onClick(view: View, contact: Contact) {
                val contactDetailFragment = ContactDetailFragment.newInstance(contact)

                contactDetailFragment.onFavoriteChangeListener = this@ContactListFragment
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment, contactDetailFragment)
                    .addToBackStack(null)
                    .commit()
            }

            override fun onStarClick(view: View, contact: Contact) {
                ContactBookmarkManager.toggleFavoriteContact(contact)
                onFavoriteChanged()
            }

        }

        //연락처 그룹 리싸이클러뷰 설정
        groupAdapter.onStartDetailListener = this@ContactListFragment
        groupAdapter.onFavoriteChangeListener = this@ContactListFragment
        binding.recyclerviewList.run {
            adapter = groupAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(text: Editable?) {
                Log.d(TAG_LIST, "afterTextChanged, ${text.toString()}")

                //즐겨찾기 리싸이클러뷰 검색 결과 필터링하기
               favoriteAdapter.changeDataset(
                    if (text.isNullOrBlank()) contactBookmarkList
                    else ContactBookmarkManager.getFilteredList(text.toString())
                )
                //연락처 그룹 리싸이클러뷰 검색 결과 필터링하기
                groupAdapter.changeDataset(
                    if (text.isNullOrBlank()) contactGroupList
                    else ContactGroupManager.getFilteredGroupList(text.toString())
                )
            }
        })

        binding.floatingBtn.setOnClickListener {
            Log.d(TAG_LIST, "floating action button clicked")

            val addContactFragment = AddContactDialogFragment()
            addContactFragment.addContactListner = groupAdapter
            requireActivity().supportFragmentManager
                .beginTransaction()
                .add(R.id.nav_host_fragment, addContactFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onStartDetailFragment(contact: Contact) {
        val contactDetailFragment = ContactDetailFragment.newInstance(contact)

        contactDetailFragment.onFavoriteChangeListener = this@ContactListFragment
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, contactDetailFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onFavoriteChanged() {
        Log.d(Constants.TAG_MY_GROUP_ADAPTER, "on favorite changed")
        favoriteAdapter.notifyDataSetChanged()
        groupAdapter.notifyDataSetChanged()
    }


    override fun onResume() {
        Log.d(TAG_LIST, "ContactListFragmentList onResume()")
        favoriteAdapter?.notifyDataSetChanged()
        groupAdapter?.notifyDataSetChanged()
        super.onResume()
    }


}