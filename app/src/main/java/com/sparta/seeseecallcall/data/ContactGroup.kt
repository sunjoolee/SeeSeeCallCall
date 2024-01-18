package com.sparta.seeseecallcall.data

data class ContactGroup (
    val groupName         :String,
    val contactList: MutableList<Contact>
){}