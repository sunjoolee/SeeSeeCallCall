package com.sparta.seeseecallcall.data

import android.net.Uri

data class Contact(
    val profileImage: Uri? = null,
    val name: String,
    val mbti:String = "????",
    val phoneNumber:String,
    val email:String,
    val birthDate:String,
    var favorite:Boolean = false
) {}
