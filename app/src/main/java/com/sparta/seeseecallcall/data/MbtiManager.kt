package com.sparta.seeseecallcall.data

import com.sparta.seeseecallcall.R

object MbtiManager {
    val mbtiList:List<Mbti> = initMbtiList()
    fun getCompatibility(mbti:String): CompatibilityText{
        if((mbti=="????")||(ContactManager.myContact.mbti == "????"))
            return CompatibilityText.UN_KNOWN

        val myMbti = mbtiList.find { it.type == ContactManager.myContact.mbti}!!
        if(myMbti.bestCompatibility.contains(mbti))  return CompatibilityText.BEST
        if(myMbti.goodCompatibility.contains(mbti))  return CompatibilityText.GOOD
        if(myMbti.sosoCompatibility.contains(mbti))  return CompatibilityText.SOSO
        if(myMbti.badCompatibility.contains(mbti))  return CompatibilityText.BAD

        return CompatibilityText.UN_KNOWN
    }
    fun getCompatibilityColor(mbti:String){
        //TODO getCompatibility() 결과에 기반하여 궁합 색 리소스 ID 반환
    }

    private fun initMbtiList():List<Mbti> = listOf(
        Mbti( //0. INFP
            type="INFP",
            short_description = "잔다르크형",
            long_description = "이상적인 세상을 만들어 가는 사람들",
            bestCompatibility = listOf("ENFJ"),
            goodCompatibility = listOf("INFP","ENFP","INFJ","INTJ","INTP","ENTP"),
            sosoCompatibility = listOf(),
            badCompatibility = listOf("ISFP","ESFP","ISTP","ESTP","ISFJ","ESFJ","ISTJ","ESTJ")
            )
    )

    //-------------기존 mbti 관련 코드 (빌드 되도록 남겨둠)-------------
    val mbtiId: Map<String, Int> = initMbtiId()
    val compatibilityColor: List<List<CompatibilityColor>> = initCompatibilityColor()
    private fun initMbtiId(): Map<String, Int> = mapOf(
        "INFP" to 0,
        "ENFP" to 1,
        "INFJ" to 2,
        "ENFJ" to 3,
        "INTJ" to 4,
        "ENTJ" to 5,
        "INTP" to 6,
        "ENTP" to 7,
        "ISFP" to 8,
        "ESFP" to 9,
        "ISTP" to 10,
        "ESTP" to 11,
        "ISFJ" to 12,
        "ESFJ" to 13,
        "ISTJ" to 14,
        "ESTJ" to 15
    )

    private fun initCompatibilityColor(): List<List<CompatibilityColor>> = listOf(
        listOf( //0. INFP 궁합
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.BEST,
            CompatibilityColor.GOOD,
            CompatibilityColor.BEST,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD
        ),
        listOf( //1. ENFP 궁합
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.BEST,
            CompatibilityColor.GOOD,
            CompatibilityColor.BEST,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD
        ),
        listOf( //2. INFJ 궁합
            CompatibilityColor.GOOD,
            CompatibilityColor.BEST,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.BEST,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD
        ),
        listOf( //3. ENFJ 궁합
            CompatibilityColor.BEST,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.BEST,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD
        ),
        listOf( //4. INTJ 궁합
            CompatibilityColor.GOOD,
            CompatibilityColor.BEST,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.BEST,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO
        ),
        listOf( //5. ENTJ 궁합
            CompatibilityColor.BEST,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.BEST,
            CompatibilityColor.GOOD,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO
        ),
        listOf( //6. INTP 궁합
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.BEST,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO
        ),
        listOf( //7. ENTP 궁합
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.BEST,
            CompatibilityColor.GOOD,
            CompatibilityColor.BEST,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO
        ),
        listOf( //8. ISFP 궁합
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BEST,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.BEST,
            CompatibilityColor.SOSO,
            CompatibilityColor.BEST
        ),
        listOf( //9. ESFP 궁합
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.BEST,
            CompatibilityColor.SOSO,
            CompatibilityColor.BEST,
            CompatibilityColor.SOSO
        ),
        listOf( //10. ISTP 궁합
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.BEST,
            CompatibilityColor.SOSO,
            CompatibilityColor.BEST
        ),
        listOf( //11. ESTP 궁합
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.BEST,
            CompatibilityColor.SOSO,
            CompatibilityColor.BEST,
            CompatibilityColor.SOSO
        ),
        listOf( //12. ISFJ 궁합
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.BEST,
            CompatibilityColor.SOSO,
            CompatibilityColor.BEST,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD
        ),
        listOf( //13. ESFJ 궁합
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.BEST,
            CompatibilityColor.SOSO,
            CompatibilityColor.BEST,
            CompatibilityColor.SOSO,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD
        ),
        listOf( //14. ISTJ 궁합
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.BEST,
            CompatibilityColor.SOSO,
            CompatibilityColor.BEST,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD
        ),
        listOf( //15. ESTJ 궁합
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.BAD,
            CompatibilityColor.SOSO,
            CompatibilityColor.SOSO,
            CompatibilityColor.BEST,
            CompatibilityColor.SOSO,
            CompatibilityColor.BEST,
            CompatibilityColor.SOSO,
            CompatibilityColor.BEST,
            CompatibilityColor.SOSO,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD,
            CompatibilityColor.GOOD
        )
    )
}