package com.sparta.seeseecallcall.data

data class Mbti(
    val type:String,
    val short_description:String,
    val long_description:String,
    val bestCompatibility:List<String>,
    val goodCompatibility:List<String>,
    val sosoCompatibility:List<String>,
    val badCompatibility:List<String>
)
