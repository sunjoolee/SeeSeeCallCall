package com.sparta.seeseecallcall.data

object ContactBookmarkManager {

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

    fun deleteContactFromBookmark(contact: Contact) {
        if (contact.favorite) contactBookmarkList.remove(contact)
    }

    fun getFilteredList(searchText: String): MutableList<Contact> {
        val filteredList = mutableListOf<Contact>()
        contactBookmarkList.forEach { contact ->
            if ((contact.name.contains(searchText)) || (contact.mbti.contains(searchText.toUpperCase())))
                filteredList.add(contact)
        }
        return filteredList
    }
}