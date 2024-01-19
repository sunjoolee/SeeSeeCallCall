package com.sparta.seeseecallcall.data

import android.net.Uri
import com.sparta.seeseecallcall.R

object MyContactManager {
    var myContact = Contact(
        profileImage = Uri.parse("android.resource://com.sparta.seeseecallcall/"+ R.drawable.profile_iu),
        name = "아이유",
        mbti = "ISFJ",
        phoneNumber = "010-1234-1234",
        birthDate = "1993/05/16",
        email = "iu@gmail.com"
    )
}