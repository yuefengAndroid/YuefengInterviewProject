package com.example.yuefenginterviewproject.data.repository

import com.example.yuefenginterviewproject.data.entity.SearchProduct
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SearchRepository {
    lateinit var dbReference: DatabaseReference

    fun getProductsData(task: OnProductsFinish) {
        val myTreasureBoxList = mutableListOf<SearchProduct>()
        dbReference = FirebaseDatabase.getInstance().getReference("Data")
        dbReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (item in snapshot.child("product").children) {
                        val activityBannerItems = item.key
                        val entity = snapshot.child("product").child(activityBannerItems.toString())
                            .getValue(SearchProduct::class.java)
                        if (entity != null) {
                            myTreasureBoxList.add(entity)
                        }
                    }
                    task.onFinish(myTreasureBoxList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    interface OnProductsFinish {
        fun onFinish(productsEntity: MutableList<SearchProduct>)
    }

    fun getTagLists(): Pair<List<String>, List<String>> {
        val list1 = listOf("日安玩美", "資生堂", "禮盒", "國際牌冰箱", "衛生紙", "溫泉泡湯券", "貓砂")
        val list2 = listOf("衛生紙", "資生堂")
        return Pair(list1, list2)
    }

}