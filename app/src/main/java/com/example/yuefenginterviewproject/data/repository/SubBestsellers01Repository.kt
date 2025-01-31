package com.example.yuefenginterviewproject.data.repository

import com.example.yuefenginterviewproject.data.entity.BestsellersEntity

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SubBestsellers01Repository {
    lateinit var dbReference: DatabaseReference

    fun getSubBestsellers01Data(task: OnProductsFinish) {
        val myTreasureBoxList = mutableListOf<BestsellersEntity>()
        dbReference = FirebaseDatabase.getInstance().getReference("Data")
        dbReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (item in snapshot.child("Ranking").children) {
                        val activityBannerItems = item.key
                        val entity = snapshot.child("Ranking").child(activityBannerItems.toString())
                            .getValue(BestsellersEntity::class.java)
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

    fun getSubBestsellers02Data(task: OnProductsFinish) {
        val myTreasureBoxList = mutableListOf<BestsellersEntity>()
        dbReference = FirebaseDatabase.getInstance().getReference("Data")
        dbReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (item in snapshot.child("Ranking2").children) {
                        val activityBannerItems = item.key
                        val entity = snapshot.child("Ranking2").child(activityBannerItems.toString())
                            .getValue(BestsellersEntity::class.java)
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
        fun onFinish(bestsellersEntity: MutableList<BestsellersEntity>)
    }
}