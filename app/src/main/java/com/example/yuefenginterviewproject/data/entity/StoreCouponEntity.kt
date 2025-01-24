package com.example.yuefenginterviewproject.data.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class StoreCouponEntity(
    var active: Boolean? = false,
    var activity_code: String? = "",
    var amount: Int? = -1,
    var coupon_info: String? = "",
    var coupon_name: String? = "",
    var coupon_photo: String? = "",
    var end_time: String? = "",
    var num: Int? = -1,
    var start_time: String? = "",
    var store_coupon_id: Int? = -1,
    var store_id: Int? = -1,
    var store_name: String? = "",
    var store_logo: String? = "",
    var address: String? = "",
    var webviewUrl: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(active)
        parcel.writeString(activity_code)
        parcel.writeValue(amount)
        parcel.writeString(coupon_info)
        parcel.writeString(coupon_name)
        parcel.writeString(coupon_photo)
        parcel.writeString(end_time)
        parcel.writeValue(num)
        parcel.writeString(start_time)
        parcel.writeValue(store_coupon_id)
        parcel.writeValue(store_id)
        parcel.writeString(store_name)
        parcel.writeString(store_logo)
        parcel.writeString(address)
        parcel.writeString(webviewUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StoreCouponEntity> {
        override fun createFromParcel(parcel: Parcel): StoreCouponEntity {
            return StoreCouponEntity(parcel)
        }

        override fun newArray(size: Int): Array<StoreCouponEntity?> {
            return arrayOfNulls(size)
        }
    }
}