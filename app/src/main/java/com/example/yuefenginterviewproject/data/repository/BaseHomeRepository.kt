package com.example.yuefenginterviewproject.data.repository

class BaseHomeRepository {

    fun getTabTitles(): List<String> {
        return listOf(
            "首頁", "直播", "電視購物", "保健/醫療", "美食/票券",
            "保養/彩妝", "旅遊/住宿券", "日用清潔", "生活家電", "寵物"
        )
    }

}