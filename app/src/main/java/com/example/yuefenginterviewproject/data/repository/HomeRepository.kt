package com.example.yuefenginterviewproject.data.repository

import com.example.yuefenginterviewproject.data.entity.HomeBannerEntity
import com.example.yuefenginterviewproject.data.entity.ProductsEntity
import com.example.yuefenginterviewproject.data.entity.SubHotEntity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeRepository {
    lateinit var dbReference: DatabaseReference

    //廣告輪播
    fun getHomeBannerData(task: OnHomeBannerFinish) {
        val adBannerList = mutableListOf<HomeBannerEntity>()

        dbReference = FirebaseDatabase.getInstance().getReference("Data")

        dbReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (item in snapshot.child("HomeBanner02").children) {
                        val activityBannerItems = item.key
                        val entity =
                            snapshot.child("HomeBanner02").child(activityBannerItems.toString())
                                .getValue(HomeBannerEntity::class.java)
                        if (entity != null) {
                            adBannerList.add(entity)
                        }
                    }
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

    fun getBanner04Data(task: OnBanner04Finish) {
        val myTreasureBoxList = mutableListOf<SubHotEntity>()
        dbReference = FirebaseDatabase.getInstance().getReference("Data")
        dbReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (item in snapshot.child("HomeBanner03").children) {
                        val activityBannerItems = item.key
                        val entity =
                            snapshot.child("HomeBanner03").child(activityBannerItems.toString())
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

    fun getHomeLastData(task: OnProductsFinish) {
        val myTreasureBoxList = mutableListOf<ProductsEntity>()
        dbReference = FirebaseDatabase.getInstance().getReference("Data")
        dbReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (item in snapshot.child("product").children) {
                        val activityBannerItems = item.key
                        val entity = snapshot.child("product").child(activityBannerItems.toString())
                            .getValue(ProductsEntity::class.java)
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

    interface OnBanner04Finish {
        fun onFinish(subHotEntity: MutableList<SubHotEntity>)
    }

    interface OnHomeBannerFinish {
        fun onFinish(homeBannerEntity: MutableList<HomeBannerEntity>)
    }

    interface OnProductsFinish {
        fun onFinish(productsEntity: MutableList<ProductsEntity>)
    }
}