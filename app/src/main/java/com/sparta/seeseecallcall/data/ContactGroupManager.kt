package com.sparta.seeseecallcall.data

import com.sparta.seeseecallcall.getInitialSound

object ContactGroupManager {

    var contactGroupList : List<ContactGroup> = listOf<ContactGroup>(
        ContactGroup("0-9", mutableListOf()),
        ContactGroup("ㄱ", mutableListOf<Contact>()),
        ContactGroup("ㄲ", mutableListOf<Contact>()),
        ContactGroup("ㄴ", mutableListOf<Contact>()),
        ContactGroup("ㄷ", mutableListOf<Contact>()),
        ContactGroup("ㄸ", mutableListOf<Contact>()),
        ContactGroup("ㄹ", mutableListOf<Contact>()),
        ContactGroup("ㅁ", mutableListOf<Contact>()),
        ContactGroup("ㅂ", mutableListOf<Contact>()),
        ContactGroup("ㅃ", mutableListOf<Contact>()),
        ContactGroup("ㅅ", mutableListOf<Contact>()),
        ContactGroup("ㅆ", mutableListOf<Contact>()),
        ContactGroup("ㅇ", mutableListOf<Contact>()),
        ContactGroup("ㅈ", mutableListOf<Contact>()),
        ContactGroup("ㅉ", mutableListOf<Contact>()),
        ContactGroup("ㅊ", mutableListOf<Contact>()),
        ContactGroup("ㅋ", mutableListOf<Contact>()),
        ContactGroup("ㅌ", mutableListOf<Contact>()),
        ContactGroup("ㅍ", mutableListOf<Contact>()),
        ContactGroup("ㅎ", mutableListOf<Contact>()),
        ContactGroup("a-Z", mutableListOf<Contact>()),
        ContactGroup("#", mutableListOf<Contact>())
    )

    init{
        ContactManager.contactList.forEach { contact ->
            contactGroupList.find{
                it.groupName == getContactGroupName(contact)
            }!!.contactList.add(contact)
        }
    }

    fun getContactGroupName(contact:Contact):String = contact.name.run{
        if(contact.name.matches("""^[0-9]""".toRegex())) "0-9"
        else if(contact.name.matches("""^[a-z|A-Z]""".toRegex())) "a-Z"
        else {
            contact.name.getInitialSound() ?: "#"
        }
    }

}