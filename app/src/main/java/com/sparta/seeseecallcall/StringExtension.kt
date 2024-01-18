package com.sparta.seeseecallcall

fun String.getInitialSound(): String? {
    val chs = arrayOf(
        "ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ",
        "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ",
        "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ",
        "ㅋ", "ㅌ", "ㅍ", "ㅎ"
    )
    if (this.isNotEmpty()) {
        val chName = this[0]
        if (chName.code >= 0xAC00) {
            val uniVal = chName.code - 0xAC00
            val cho = (((uniVal - uniVal % 28) / 28) / 21)
            return chs[cho]
        }
    }
    return null
}