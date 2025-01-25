package com.example.yuefenginterviewproject.data.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ProductsEntity(
    var ImgUrl: String? = "",

    var Title: String? = "",

    var Money: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(ImgUrl)
        parcel.writeString(Title)
        parcel.writeString(Money)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductsEntity> {
        override fun createFromParcel(parcel: Parcel): ProductsEntity {
            return ProductsEntity(parcel)
        }

        override fun newArray(size: Int): Array<ProductsEntity?> {
            return arrayOfNulls(size)
        }
    }
}