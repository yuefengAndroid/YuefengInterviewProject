package com.example.yuefenginterviewproject.data.repository

import com.example.yuefenginterviewproject.data.entity.StoreCouponEntity
import com.example.yuefenginterviewproject.data.entity.SubHotEntity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeRepository {
    lateinit var dbReference: DatabaseReference

    //廣告輪播
    fun getAdBannerData(task: MemberRepository.OnActivityBannerFinish) {
        val adBannerList = mutableListOf<StoreCouponEntity>()

        dbReference = FirebaseDatabase.getInstance().getReference("Data")

        dbReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (item in snapshot.child("ActivityBanner").children) {
                        val activityBannerItems = item.key
                        val entity =
                            snapshot.child("ActivityBanner").child(activityBannerItems.toString())
                                .getValue(StoreCouponEntity::class.java)
                        if (entity != null) {
                            adBannerList.add(entity)
                        }
                    }
                    adBannerList.sortedBy { it.num }
                    task.onFinish(adBannerList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    fun getSubHotData(task: OnSubHotFinish) {
        val myTreasureBoxList = mutableListOf<SubHotEntity>()
        dbReference = FirebaseDatabase.getInstance().getReference("Data")
        dbReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (item in snapshot.child("HotEvent").children) {
                        val activityBannerItems = item.key
                        val entity =
                            snapshot.child("HotEvent").child(activityBannerItems.toString())
                                .getValue(SubHotEntity::class.java)
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

    interface OnSubHotFinish {
        fun onFinish(subHotEntity: MutableList<SubHotEntity>)
    }

    interface OnActivityBannerFinish {
        fun onFinish(storeCouponEntity: MutableList<StoreCouponEntity>)
    }
}