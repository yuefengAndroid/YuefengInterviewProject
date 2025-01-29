package com.example.yuefenginterviewproject.data.repository

class BestsellersRepository {

    fun getTabTitles(): List<String> {
        return listOf(
            "全館", "電視購物", "保健", "美妝", "手機",
            "電腦", "廚電", "日用", "美食", "運動"
        )
    }

}