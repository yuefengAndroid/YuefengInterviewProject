package com.example.yuefenginterviewproject.data.repository

import com.example.yuefenginterviewproject.data.entity.EventEntity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MessageEventRepository {
    lateinit var dbReference: DatabaseReference

    fun getEventsData(task: OnProductsFinish) {
        val myTreasureBoxList = mutableListOf<EventEntity>()
        dbReference = FirebaseDatabase.getInstance().getReference("Data")
        dbReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (item in snapshot.child("Message").children) {
                        val activityBannerItems = item.key
                        val entity = snapshot.child("Message").child(activityBannerItems.toString())
                            .getValue(EventEntity::class.java)
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
        fun onFinish(productsEntity: MutableList<EventEntity>)
    }
}