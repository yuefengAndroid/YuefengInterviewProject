package com.example.yuefenginterviewproject.data.repository

import com.example.yuefenginterviewproject.data.entity.NavbarEntity
import com.example.yuefenginterviewproject.data.entity.ProductsEntity
import com.example.yuefenginterviewproject.data.entity.StoreCouponEntity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MemberRepository {
    lateinit var dbReference: DatabaseReference

    //廣告輪播
    fun getAdBannerData(task: OnActivityBannerFinish) {
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

    //防詐騙提醒區
    fun getOthersData(task: OnNavbarFinish) {
        val myTreasureBoxList = mutableListOf<NavbarEntity>()
        dbReference = FirebaseDatabase.getInstance().getReference("Data")
        dbReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (item in snapshot.child("Navbar5").children) {
                        val activityBannerItems = item.key
                        val entity = snapshot.child("Navbar5").child(activityBannerItems.toString())
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

    //幫助中心
    fun getHelpBoxData(task: OnNavbarFinish) {
        val myTreasureBoxList = mutableListOf<NavbarEntity>()
        dbReference = FirebaseDatabase.getInstance().getReference("Data")
        dbReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (item in snapshot.child("Navbar4").children) {
                        val activityBannerItems = item.key
                        val entity = snapshot.child("Navbar4").child(activityBannerItems.toString())
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

    //專屬推薦
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

    interface OnActivityBannerFinish {
        fun onFinish(storeCouponEntity: MutableList<StoreCouponEntity>)
    }

    interface OnNavbarFinish {
        fun onFinish(navbarEntity: MutableList<NavbarEntity>)
    }

    interface OnProductsFinish {
        fun onFinish(productsEntity: MutableList<ProductsEntity>)
    }
}