package com.sparta.seeseecallcall.data

import android.net.Uri
import com.sparta.seeseecallcall.getInitialSound

object ContactGroupManager {

    var contactGroupList: MutableList<ContactGroup> = mutableListOf<ContactGroup>(
        ContactGroup("0-9", mutableListOf()),
        ContactGroup("ㄱ", mutableListOf<Contact>()),
        ContactGroup("ㄲ", mutableListOf<Contact>()),
        ContactGroup("ㄴ", mutableListOf<Contact>()),
        ContactGroup("ㄷ", mutableListOf<Contact>()),
        ContactGroup("ㄸ", mutableListOf<Contact>()),
        ContactGroup("ㄹ", mutableListOf<Contact>()),
        ContactGroup("ㅁ", mutableListOf<Contact>()),
        ContactGroup("ㅂ", mutableListOf<Contact>()),
        ContactGroup("ㅃ", mutableListOf<Contact>()),
        ContactGroup("ㅅ", mutableListOf<Contact>()),
        ContactGroup("ㅆ", mutableListOf<Contact>()),
        ContactGroup("ㅇ", mutableListOf<Contact>()),
        ContactGroup("ㅈ", mutableListOf<Contact>()),
        ContactGroup("ㅉ", mutableListOf<Contact>()),
        ContactGroup("ㅊ", mutableListOf<Contact>()),
        ContactGroup("ㅋ", mutableListOf<Contact>()),
        ContactGroup("ㅌ", mutableListOf<Contact>()),
        ContactGroup("ㅍ", mutableListOf<Contact>()),
        ContactGroup("ㅎ", mutableListOf<Contact>()),
        ContactGroup("a-Z", mutableListOf<Contact>()),
        ContactGroup("#", mutableListOf<Contact>())
    )

    init {
        initContactList().forEach { contact ->
            contactGroupList.find {
                it.groupName == getContactGroupName(contact)
            }!!.contactList.add(contact)
        }
    }

    private fun getContactGroupName(contact: Contact): String = contact.name.run {
        if (contact.name.matches("""^[0-9]""".toRegex())) "0-9"
        else if (contact.name.matches("""^[a-z|A-Z]""".toRegex())) "a-Z"
        else {
            contact.name.getInitialSound() ?: "#"
        }
    }

    fun addNewContact(
        profileImage: Uri? = null,
        name: String,
        mbti: String = "????",
        phoneNumber: String,
        email: String,
        birthDate: String,
    ) {
        val newContact = Contact(profileImage, name, mbti, phoneNumber, email, birthDate)
        contactGroupList.find {
            it.groupName == getContactGroupName(newContact)
        }!!.contactList.add(newContact)
    }

    fun deleteContactFromGroup(contact: Contact) {
        contactGroupList.find {
            it.groupName == getContactGroupName(contact)
        }!!.contactList.remove(contact)
    }

    fun getFilteredGroupList(searchText: String): MutableList<ContactGroup> {
        val filteredGroupList = mutableListOf<ContactGroup>()
        contactGroupList.forEach {
            val filteredList = mutableListOf<Contact>()
            it.contactList.forEach { contact ->
                if ((contact.name.contains(searchText)) || (contact.mbti.contains(searchText.toUpperCase())))
                    filteredList.add(contact)
            }
            filteredGroupList.add(ContactGroup(it.groupName, filteredList))
        }
        return filteredGroupList
    }

    private fun initContactList(): MutableList<Contact> = mutableListOf<Contact>(
        Contact(
            name = "정정원",
            mbti = "INFP",
            phoneNumber = "010-1111-1111",
            email = "jungjungone@gmail.com",
            birthDate = "1996/01/01",
        ),
        Contact(
            name = "황은경",
            mbti = "ENFP",
            phoneNumber = "010-2222-2222",
            email = "hwangeunkyung@gmail.com",
            birthDate = "1996/02/02",
        ),
        Contact(
            name = "신호영",
            mbti = "INFJ",
            phoneNumber = "010-3333-3333",
            email = "shinhoyoung@gmail.com",
            birthDate = "1996/03/03",
        ),
        Contact(
            name = "이소영",
            mbti = "ENFJ",
            phoneNumber = "010-4444-4444",
            email = "leesoyoung@gmail.com",
            birthDate = "1996/04/04",
        ),
        Contact(
            name = "류은석",
            mbti = "INTJ",
            phoneNumber = "010-5555-5555",
            email = "rueeunsuk@naver.com",
            birthDate = "1996/05/05",
        ),
        Contact(
            name = "김인경",
            mbti = "ENTJ",
            phoneNumber = "010-6666-6666",
            email = " kiminkyung@naver.com",
            birthDate = "1996/06/06",
        ),
        Contact(
            name = "손시윤",
            mbti = "INTP",
            phoneNumber = "010-7777-7777",
            email = "sonsiyoun@naver.com",
            birthDate = "1996/07/07",
        ),
        Contact(
            name = "정현진",
            mbti = "ENTP",
            phoneNumber = "010-8888-8888",
            email = "junghyunjin@naver.com",
            birthDate = "1996/08/08",
        ),
        Contact(
            name = "강현우",
            mbti = "ISFP",
            phoneNumber = "010-9999-9999",
            email = "kanghyunwoo@hotmail.com",
            birthDate = "1996/09/09",
        ),
        Contact(
            name = "임호준",
            mbti = "ESFP",
            phoneNumber = "010-1010-1010",
            email = "imhojun@hotmail.com",
            birthDate = "1996/10/10",
        ),
        Contact(
            name = "배정숙",
            mbti = "ISTP",
            phoneNumber = "010-1212-1212",
            email = "baejungsook@hotmail.com",
            birthDate = "1996/12/12",
        ),
        Contact(
            name = "성도희",
            mbti = "ESTP",
            phoneNumber = "010-1313-1313",
            email = "sungdohee@hotmail.com",
            birthDate = "1996/01/13",
        ),
        Contact(
            name = "남미희",
            mbti = "ISFJ",
            phoneNumber = "010-1414-1414",
            email = "nammeehee@hotmail.com",
            birthDate = "1996/02/14",
        ),
        Contact(
            name = "정해빈",
            mbti = "ESFJ",
            phoneNumber = "010-1515-1515",
            email = "junghaebin@hotmail.com",
            birthDate = "1996/03/15",
        ),
        Contact(
            name = "한준철",
            mbti = "ISTJ",
            phoneNumber = "010-1616-1616",
            email = "hanjunchul@hotmail.com",
            birthDate = "1996/04/16",
        ),
        Contact(
            name = "양선민",
            mbti = "ESTJ",
            phoneNumber = "010-1717-1717",
            email = "yangsunmin@hotmail.com",
            birthDate = "1996/05/17",
        ),
        Contact(
            name = "최아림",
            mbti = "????",
            phoneNumber = "010-1818-1818",
            email = "choiarim@gmail.com",
            birthDate = "1996/06/18",
        )
    )

}