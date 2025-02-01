package com.example.yuefenginterviewproject.data.repository

import com.example.yuefenginterviewproject.data.entity.ProductsEntity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TvSubRepository {
    lateinit var dbReference: DatabaseReference

    fun getProductsData(task: OnProductsFinish) {
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

    interface OnProductsFinish {
        fun onFinish(productsEntity: MutableList<ProductsEntity>)
    }
}