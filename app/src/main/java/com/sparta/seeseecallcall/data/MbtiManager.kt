package com.sparta.seeseecallcall.data

object MbtiManager {
    val mbtiList:List<Mbti> = initMbtiList()

    fun getCompatibility(mbti:String): CompatibilityText{
        if((mbti=="????")||(MyContactManager.myContact.mbti == "????"))
            return CompatibilityText.UN_KNOWN

        val myMbti = mbtiList.find { it.type == MyContactManager.myContact.mbti}!!
        if(myMbti.bestCompatibility.contains(mbti))  return CompatibilityText.BEST
        if(myMbti.goodCompatibility.contains(mbti))  return CompatibilityText.GOOD
        if(myMbti.sosoCompatibility.contains(mbti))  return CompatibilityText.SOSO
        if(myMbti.badCompatibility.contains(mbti))  return CompatibilityText.BAD

        return CompatibilityText.UN_KNOWN
    }

    fun getCompatibilityColor(mbti:String):Int =
        when(getCompatibility(mbti)){
            CompatibilityText.UN_KNOWN -> CompatibilityColor.UN_KNOWN.color
            CompatibilityText.BEST -> CompatibilityColor.BEST.color
            CompatibilityText.GOOD -> CompatibilityColor.GOOD.color
            CompatibilityText.SOSO -> CompatibilityColor.SOSO.color
            CompatibilityText.BAD -> CompatibilityColor.BAD.color
        }

    private fun initMbtiList():List<Mbti> = listOf(
        Mbti( //1. INFP
            type="INFP",
            short_description = "잔다르크형",
            long_description = "이상적인 세상을 만들어 가는 사람들",
            bestCompatibility = listOf("ENFJ", "ENTJ"),
            goodCompatibility = listOf("INFP","ENFP","INFJ","INTJ","INTP","ENTP"),
            sosoCompatibility = listOf(),
            badCompatibility = listOf("ISFP","ESFP","ISTP","ESTP","ISFJ","ESFJ","ISTJ","ESTJ")
        ),
        Mbti( //2. ENFP
            type="ENFP",
            short_description = "스파크형",
            long_description = "열정적으로 새로운 관계를 만드는 사람들",
            bestCompatibility = listOf("INFJ", "INTJ"),
            goodCompatibility = listOf("INFP","ENFP","ENFJ","INTP","ENTP"),
            sosoCompatibility = listOf(),
            badCompatibility = listOf("ISFP","ESFP","ISTP","ESTP","ISFJ","ESFJ","ISTJ","ESTJ")
        ),
        Mbti( //3. INFJ
            type="INFJ",
            short_description = "예언자형",
            long_description = "사람과 관련된 뛰어난 통찰력을 가지고 있는 사람들",
            bestCompatibility = listOf("ENFP", "ENTP"),
            goodCompatibility = listOf("INFP","INFJ","ENFJ","INTJ","ENTJ","INTP"),
            sosoCompatibility = listOf(),
            badCompatibility = listOf("ISFP","ESFP","ISTP","ESTP","ISFJ","ESFJ","ISTJ","ESTJ")
        ),
        Mbti( //4. ENFJ
            type="ENFJ",
            short_description = "언변능숙형",
            long_description = "타인의 성장을 도모하고 협동하는 사람들",
            bestCompatibility = listOf("INFP", "ISFP"),
            goodCompatibility = listOf("ENFP","INFJ","ENFJ","INTJ","ENTJ","INTP","ENTP"),
            sosoCompatibility = listOf(),
            badCompatibility = listOf("ESFP","ISTP","ESTP","ISFJ","ESFJ","ISTJ","ESTJ")
        ),
        Mbti( //5. INTJ
            type="INTJ",
            short_description = "과학자형",
            long_description = "전체적인 부분을 조합하여 비전을 제시하는 사람들",
            bestCompatibility = listOf("ENFP", "ENTP"),
            goodCompatibility = listOf("INFP","INFJ","ENFJ","INTJ","ENTJ","INTP"),
            sosoCompatibility = listOf("ISFP", "ESFP","ISTP","ESTP","ISFJ","ESFJ","ISTJ","ESTJ"),
            badCompatibility = listOf()
        ),
        Mbti( //6. ENTJ
            type="ENTJ",
            short_description = "지도자형",
            long_description = "비전을 가지고 사람들을 활력적으로 이끌어가는 사람들",
            bestCompatibility = listOf("INFP", "INTP"),
            goodCompatibility = listOf("ENFP","INFJ","ENFJ","INTJ","ENTJ","ENTP"),
            sosoCompatibility = listOf("ISFP","ESFP","ISTP","ESTP","ISFJ","ESFJ","ISTJ","ESTJ"),
            badCompatibility = listOf()
        ),
        Mbti( //7. INTP
            type="INTP",
            short_description = "아이디어뱅크형",
            long_description = "비평적인 관점을 가지고 있는 뛰어난 전략가들",
            bestCompatibility = listOf("ENTJ", "ESTJ"),
            goodCompatibility = listOf("INFP","ENFP","INFJ","INTJ","INTP","ENTP"),
            sosoCompatibility = listOf("ISFP","ESFP","ISTP","ESTP","ISFJ","ESFJ","ISTJ","ESTJ"),
            badCompatibility = listOf()
        ),
        Mbti( //8. ENTP
            type="ENTP",
            short_description = "발명가형",
            long_description = "풍부한 상상력을 가지고 새로운 것에 도전하는 사람들",
            bestCompatibility = listOf("INFJ", "INTJ"),
            goodCompatibility = listOf("INFP","ENFP","ENFJ","ENTJ","INTP","ENTP"),
            sosoCompatibility = listOf("ISFP","ESFP","ISTP","ESTP","ISFJ","ESFJ","ISTJ","ESTJ"),
            badCompatibility = listOf()
        ),
        Mbti( //9. ISFP
            type="ISFP",
            short_description = "성인군자형",
            long_description = "따뜻한 감성을 가지고 있는 겸손한 사람들",
            bestCompatibility = listOf("ENTJ", "ESFJ", "ESTJ"),
            goodCompatibility = listOf(),
            sosoCompatibility = listOf("INTJ","ENTJ","INTP","ENTP","ISFP","ESFP","ISTP","ESTP","ISFJ","ISTJ"),
            badCompatibility = listOf("INFP", "ENFP", "INFJ")
        ),
        Mbti( //10. ESFP
            type="ESFP",
            short_description = "사교적인 유형",
            long_description = "분위기를 고조시키는 우호적 사람들",
            bestCompatibility = listOf("ISFJ", "ISTJ"),
            goodCompatibility = listOf(),
            sosoCompatibility = listOf("INTJ","ENTJ","INTP","ENTP","ISFP","ESFP","ISTP","ESTP","ESFJ","ESTJ"),
            badCompatibility = listOf("INFP", "ENFP", "INFJ", "ENFJ")
        ),
        Mbti( //11. ISTP
            type="ISTP",
            short_description = "백과사전형",
            long_description = "논리적이고 뛰어난 상황 적응력을 가지고 있는 사람들",
            bestCompatibility = listOf("ESFJ", "ESTJ"),
            goodCompatibility = listOf(),
            sosoCompatibility = listOf("INTJ","ENTJ","INTP","ENTP","ISFP","ESFP","ISTP","ESTP","ISFJ","ISTJ",),
            badCompatibility = listOf("INFP", "ENFP", "INFJ", "ENFJ")
        ),
        Mbti( //12. ESTP
            type="ESTP",
            short_description = "수완좋은 활동가형",
            long_description = "친구, 운동, 음식 등 다양한 활동을 선호하는 사람들",
            bestCompatibility = listOf("ISFJ", "ISTJ"),
            goodCompatibility = listOf(),
            sosoCompatibility = listOf("INTJ","ENTJ","INTP","ENTP","ISFP","ESFP","ISTP","ESTP","ESFJ","ESTJ"),
            badCompatibility = listOf("INFP", "ENFP", "INFJ", "ENFJ")
        ),
        Mbti( //13. ISFJ
            type="ISFJ",
            short_description = "임금 뒤편의 권력형",
            long_description = "성실하고 온화하며 협조를 잘하는 사람들",
            bestCompatibility = listOf("ESFP", "ESTP"),
            goodCompatibility = listOf("ISFJ", "ESFJ", "ISTJ", "ESTJ"),
            sosoCompatibility = listOf("INTJ","ENTJ","INTP","ENTP","ISFP","ISTP"),
            badCompatibility = listOf("INFP", "ENFP", "INFJ", "ENFJ")
        ),
        Mbti( //14. ESFJ
            type="ESFJ",
            short_description = "친선도모형",
            long_description = "친절과 현실감을 바탕으로 타인에게 봉사하는 사람들",
            bestCompatibility = listOf("ISFP", "ISTP"),
            goodCompatibility = listOf("ISFJ", "ESFJ", "ISTJ", "ESTJ"),
            sosoCompatibility = listOf("INTJ","ENTJ","INTP","ENTP","ESFP","ESTP"),
            badCompatibility = listOf("INFP", "ENFP", "INFJ", "ENFJ")
        ),
        Mbti( //15. ISTJ
            type="ISTJ",
            short_description = "세상의 소금형",
            long_description = "한번 시작한 일은 끝까지 해내는 사람들",
            bestCompatibility = listOf("ESFP", "ESTP"),
            goodCompatibility = listOf("ISFJ", "ESFJ", "ISTJ", "ESTJ"),
            sosoCompatibility = listOf("INTJ","ENTJ","INTP","ENTP","ISFP","ISTP"),
            badCompatibility = listOf("INFP", "ENFP", "INFJ", "ENFJ")
        ),
        Mbti( //16. ESTJ
            type="ESFJ",
            short_description = "사업가형",
            long_description = "사무적, 실용적, 현실적으로 일을 많이하는 사람들",
            bestCompatibility = listOf("ISFP", "ISTP"),
            goodCompatibility = listOf("ISFJ", "ESFJ", "ISTJ", "ESTJ"),
            sosoCompatibility = listOf("INTJ","ENTJ","ENTP","ESFP","ESTP"),
            badCompatibility = listOf("INFP", "ENFP", "INFJ", "ENFJ")
        ),
    )
}