package com.sparta.seeseecallcall

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sparta.seeseecallcall.data.Contact
import com.sparta.seeseecallcall.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
    }

    companion object {
        val contactList = mutableListOf<Contact>(
            Contact(
                name = "정정원",
                phoneNumber = "010-1111-1111",
                email = "jungjungone@gmail.com",
                birthDate = "1996/01/01",
                memo = "직장 동료"
            ),
            Contact(
                name = "황은경",
                phoneNumber = "010-2222-2222",
                email = "hwangeunkyung@gmail.com",
                birthDate = "1996/02/02",
                memo = "직장 동료"
            ),
            Contact(
                name = "신호영",
                phoneNumber = "010-3333-3333",
                email = "shinhoyoung@gmail.com",
                birthDate = "1996/03/03",
                memo = "직장 동료"
            ),
            Contact(
                name = "이소영",
                phoneNumber = "010-4444-4444",
                email = "leesoyoung@gmail.com",
                birthDate = "1996/04/04",
                memo = "직장 동료"
            ),
            Contact(
                name = "류은석",
                phoneNumber = "010-5555-5555",
                email = "rueeunsuk@naver.com",
                birthDate = "1996/05/05",
                memo = "고등학교 동창"
            ),
            Contact(
                name = "김인경",
                phoneNumber = "010-6666-6666",
                email = " kiminkyung@naver.com",
                birthDate = "1996/06/06",
                memo = "고등학교 동창"
            ),
            Contact(
                name = "손시윤",
                phoneNumber = "010-7777-7777",
                email = "sonsiyoun@naver.com",
                birthDate = "1996/07/07",
                memo = "고등학교 동창"
            ),
            Contact(
                name = "정현진",
                phoneNumber = "010-8888-8888",
                email = "junghyunjin@naver.com",
                birthDate = "1996/08/08",
                memo = "중학교 동창"
            ),
            Contact(
                name = "강현우",
                phoneNumber = "010-9999-9999",
                email = "kanghyunwoo@hotmail.com",
                birthDate = "1996/09/09",
                memo = "중학교 동창"
            ),
            Contact(
                name = "임호준",
                phoneNumber = "010-1010-1010",
                email = "imhojun@hotmail.com",
                birthDate = "1996/10/10",
                memo = "중학교 동창"
            )
        )
        val contactBookmarkList = contactList.filter { it.favorite } as MutableList<Contact>
    }
}