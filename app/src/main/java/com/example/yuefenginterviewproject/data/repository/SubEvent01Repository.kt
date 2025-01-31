package com.example.yuefenginterviewproject.data.repository

import com.example.yuefenginterviewproject.data.entity.SubHotEntity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SubEvent01Repository {
    lateinit var dbReference: DatabaseReference

    fun getSubHotData(task: OnSubHotFinish) {
        val myTreasureBoxList = mutableListOf<SubHotEntity>()
        dbReference = FirebaseDatabase.getInstance().getReference("Data")
        dbReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (item in snapshot.child("HotEvent").children) {
                        val activityBannerItems = item.key
                        val entity = snapshot.child("HotEvent").child(activityBannerItems.toString())
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

    fun getSubHot2Data(task: OnSubHotFinish) {
        val myTreasureBoxList = mutableListOf<SubHotEntity>()
        dbReference = FirebaseDatabase.getInstance().getReference("Data")
        dbReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (item in snapshot.child("HotEvent1").children) {
                        val activityBannerItems = item.key
                        val entity = snapshot.child("HotEvent1").child(activityBannerItems.toString())
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
}