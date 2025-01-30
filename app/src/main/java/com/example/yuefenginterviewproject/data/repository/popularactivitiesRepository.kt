package com.example.yuefenginterviewproject.data.repository

class popularactivitiesRepository {

    fun getTabTitles(): List<String> {
        return listOf(
            "精選", "電視購物", "保健/醫療", "保養/彩妝", "手機/平板",
            "3C週邊", "家電", "日用清潔", "美食票券", "健身/戶外"
        )
    }
}