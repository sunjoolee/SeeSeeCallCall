package com.sparta.seeseecallcall.data

data class ContactGroup (
    val groupName:String,
    var contactList: MutableList<Contact>
){}