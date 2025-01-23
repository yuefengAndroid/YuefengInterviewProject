package com.example.yuefenginterviewproject.data.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class NavbarEntity(
    var MobileImage: String? = "",

    var Title: String? = "",

    var appLink: String? = "",

    var order: Int = -1
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(MobileImage)
        parcel.writeString(Title)
        parcel.writeString(appLink)
        parcel.writeInt(order)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NavbarEntity> {
        override fun createFromParcel(parcel: Parcel): NavbarEntity {
            return NavbarEntity(parcel)
        }

        override fun newArray(size: Int): Array<NavbarEntity?> {
            return arrayOfNulls(size)
        }
    }
}