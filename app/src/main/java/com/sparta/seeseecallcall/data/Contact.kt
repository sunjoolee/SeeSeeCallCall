package com.sparta.seeseecallcall.data

import android.net.Uri

data class Contact(
    val profileImage: Uri? = null,
    val name: String,
    val phoneNumber:String,
    val birthDate:Array<Int>,
    val memo:String,
    var favorite:Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        if (javaClass != other?.javaClass) return false
        other as Contact

        if (!birthDate.contentEquals(other.birthDate)) return false
        return true
    }

    override fun hashCode(): Int {
        return birthDate.contentHashCode()
    }
}
