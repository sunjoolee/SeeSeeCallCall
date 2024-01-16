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
            phoneNumber = "010-1111-1111",
            email = "jungjungone@gmail.com",
            birthDate = "1996/01/01",
        ),
        Contact(
            name = "황은경",
            phoneNumber = "010-2222-2222",
            email = "hwangeunkyung@gmail.com",
            birthDate = "1996/02/02",
        ),
        Contact(
            name = "신호영",
            phoneNumber = "010-3333-3333",
            email = "shinhoyoung@gmail.com",
            birthDate = "1996/03/03",
        ),
        Contact(
            name = "이소영",
            phoneNumber = "010-4444-4444",
            email = "leesoyoung@gmail.com",
            birthDate = "1996/04/04",
        ),
        Contact(
            name = "류은석",
            phoneNumber = "010-5555-5555",
            email = "rueeunsuk@naver.com",
            birthDate = "1996/05/05",
        ),
        Contact(
            name = "김인경",
            phoneNumber = "010-6666-6666",
            email = " kiminkyung@naver.com",
            birthDate = "1996/06/06",
        ),
        Contact(
            name = "손시윤",
            phoneNumber = "010-7777-7777",
            email = "sonsiyoun@naver.com",
            birthDate = "1996/07/07",
        ),
        Contact(
            name = "정현진",
            phoneNumber = "010-8888-8888",
            email = "junghyunjin@naver.com",
            birthDate = "1996/08/08",
        ),
        Contact(
            name = "강현우",
            phoneNumber = "010-9999-9999",
            email = "kanghyunwoo@hotmail.com",
            birthDate = "1996/09/09",
        ),
        Contact(
            name = "임호준",
            phoneNumber = "010-1010-1010",
            email = "imhojun@hotmail.com",
            birthDate = "1996/10/10",
        )
    )
}