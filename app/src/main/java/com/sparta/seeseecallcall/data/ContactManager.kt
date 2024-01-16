package com.sparta.seeseecallcall.data

object ContactManager{
    val myContact = Contact(
        name="아이유",
        mbti= "INFJ",
        phoneNumber = "010-1234-1234",
        birthDate = "1993/05/16",
        email = "iu@gamil.com"
    )


    val contactList = getDefaultContactList()
    val contactBookmarkList = contactList.filter { it.favorite } as MutableList<Contact>

    private fun getDefaultContactList():MutableList<Contact> = mutableListOf<Contact>(
        Contact(
            name = "정정원",
            mbti="INFP",
            phoneNumber = "010-1111-1111",
            email = "jungjungone@gmail.com",
            birthDate = "1996/01/01",
        ),
        Contact(
            name = "황은경",
            mbti="ENFP",
            phoneNumber = "010-2222-2222",
            email = "hwangeunkyung@gmail.com",
            birthDate = "1996/02/02",
        ),
        Contact(
            name = "신호영",
            mbti="INFJ",
            phoneNumber = "010-3333-3333",
            email = "shinhoyoung@gmail.com",
            birthDate = "1996/03/03",
        ),
        Contact(
            name = "이소영",
            mbti="ENFJ",
            phoneNumber = "010-4444-4444",
            email = "leesoyoung@gmail.com",
            birthDate = "1996/04/04",
        ),
        Contact(
            name = "류은석",
            mbti="INTJ",
            phoneNumber = "010-5555-5555",
            email = "rueeunsuk@naver.com",
            birthDate = "1996/05/05",
        ),
        Contact(
            name = "김인경",
            mbti="ENTJ",
            phoneNumber = "010-6666-6666",
            email = " kiminkyung@naver.com",
            birthDate = "1996/06/06",
        ),
        Contact(
            name = "손시윤",
            mbti="INTP",
            phoneNumber = "010-7777-7777",
            email = "sonsiyoun@naver.com",
            birthDate = "1996/07/07",
        ),
        Contact(
            name = "정현진",
            mbti="ENTP",
            phoneNumber = "010-8888-8888",
            email = "junghyunjin@naver.com",
            birthDate = "1996/08/08",
        ),
        Contact(
            name = "강현우",
            mbti="ISFP",
            phoneNumber = "010-9999-9999",
            email = "kanghyunwoo@hotmail.com",
            birthDate = "1996/09/09",
        ),
        Contact(
            name = "임호준",
            mbti="ESFP",
            phoneNumber = "010-1010-1010",
            email = "imhojun@hotmail.com",
            birthDate = "1996/10/10",
        ),
        Contact(
            name = "배정숙",
            mbti="ISTP",
            phoneNumber = "010-1212-1212",
            email = "baejungsook@hotmail.com",
            birthDate = "1996/12/12",
        ),
        Contact(
            name = "성도희",
            mbti="ESTP",
            phoneNumber = "010-1313-1313",
            email = "sungdohee@hotmail.com",
            birthDate = "1996/01/13",
        ),
        Contact(
            name = "남미희",
            mbti="ISFJ",
            phoneNumber = "010-1414-1414",
            email = "nammeehee@hotmail.com",
            birthDate = "1996/02/14",
        ),
        Contact(
            name = "정해빈",
            mbti="ESFJ",
            phoneNumber = "010-1515-1515",
            email = "junghaebin@hotmail.com",
            birthDate = "1996/03/15",
        ),
        Contact(
            name = "한준철",
            mbti="ISTJ",
            phoneNumber = "010-1616-1616",
            email = "hanjunchul@hotmail.com",
            birthDate = "1996/04/16",
        ),
        Contact(
            name = "양선민",
            mbti="ESTJ",
            phoneNumber = "010-1717-1717",
            email = "yangsunmin@hotmail.com",
            birthDate = "1996/05/17",
        )

    )
}