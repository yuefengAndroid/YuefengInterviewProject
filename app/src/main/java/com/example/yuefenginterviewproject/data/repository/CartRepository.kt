package com.example.yuefenginterviewproject.data.repository

import com.example.yuefenginterviewproject.data.entity.CartItemsEntity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CartRepository {
    lateinit var dbReference: DatabaseReference

    fun getCartItemsData(task: OnProductsFinish) {
        val myTreasureBoxList = mutableListOf<CartItemsEntity>()
        dbReference = FirebaseDatabase.getInstance().getReference("Data")
        dbReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (item in snapshot.child("CartItem").children) {
                        val activityBannerItems = item.key
                        val entity = snapshot.child("CartItem").child(activityBannerItems.toString())
                            .getValue(CartItemsEntity::class.java)
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
        fun onFinish(itemsEntity: MutableList<CartItemsEntity>)
    }
}