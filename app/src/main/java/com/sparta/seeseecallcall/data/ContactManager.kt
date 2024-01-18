package com.sparta.seeseecallcall.data

import android.net.Uri
import android.util.Log
import com.sparta.seeseecallcall.Constants.TAG_CONTACT_MANAGER

object ContactManager {
    val myContact = Contact(
        name = "아이유",
        mbti = "INFJ",
        phoneNumber = "010-1234-1234",
        birthDate = "1993/05/16",
        email = "iu@gamil.com"
    )

    val contactBookmarkList: MutableList<Contact> = mutableListOf<Contact>()

    fun toggleFavoriteContact(contact: Contact) {
        if (contact.favorite) {
            contact.favorite = false
            contactBookmarkList.remove(contact)
        } else {
            contact.favorite = true
            contactBookmarkList.add(contact)
        }
        contactBookmarkList.sortBy { it.name }
    }
}