package com.example.yuefenginterviewproject.data.repository

import com.example.yuefenginterviewproject.data.entity.NavbarEntity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MemberRepository {
    lateinit var dbReference: DatabaseReference
    //我的訂單
    fun getMyOrderData(task: OnNavbarFinish) {
        val navbarList = mutableListOf<NavbarEntity>()
        dbReference = FirebaseDatabase.getInstance().getReference("Data")
        dbReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (item in snapshot.child("Navbar").children) {
                        val activityBannerItems = item.key
                        val entity = snapshot.child("Navbar").child(activityBannerItems.toString())
                            .getValue(NavbarEntity::class.java)
                        if (entity != null) {
                            navbarList.add(entity)
                        }
                    }
                    task.onFinish(navbarList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    //我的券匣
    fun getMyTicketsData(task: OnNavbarFinish) {
        val myTicketsList = mutableListOf<NavbarEntity>()
        dbReference = FirebaseDatabase.getInstance().getReference("Data")
        dbReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (item in snapshot.child("Navbar3").children) {
                        val activityBannerItems = item.key
                        val entity = snapshot.child("Navbar3").child(activityBannerItems.toString())
                            .getValue(NavbarEntity::class.java)
                        if (entity != null) {
                            myTicketsList.add(entity)
                        }
                    }
                    task.onFinish(myTicketsList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    //優惠百寶箱
    fun getTreasureBoxData(task: OnNavbarFinish) {
        val myTreasureBoxList = mutableListOf<NavbarEntity>()
        dbReference = FirebaseDatabase.getInstance().getReference("Data")
        dbReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (item in snapshot.child("Navbar2").children) {
                        val activityBannerItems = item.key
                        val entity = snapshot.child("Navbar2").child(activityBannerItems.toString())
                            .getValue(NavbarEntity::class.java)
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

    interface OnNavbarFinish {
        fun onFinish(navbarEntity: MutableList<NavbarEntity>)
    }
}