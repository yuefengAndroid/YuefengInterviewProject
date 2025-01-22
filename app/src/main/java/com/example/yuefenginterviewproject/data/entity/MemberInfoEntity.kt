package com.example.yuefenginterviewproject.data.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class MemberInfoEntity(
    var mName: String? = "",
    var mPhone: String? = "",
    var mPic: String? = "",
    var mPws: String? = "",
    var mTel: String? = "",
    var mEmail: String? = "",
    var mJob: String? = "",
    var mJobName: String? = "",
    var birthdayYear: Int? = 2000,
    var birthdayMonth: Int? = 1,
    var birthdayDay: Int? = 1,
    var mAddress: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(mName)
        parcel.writeString(mPhone)
        parcel.writeString(mPic)
        parcel.writeString(mPws)
        parcel.writeString(mTel)
        parcel.writeString(mEmail)
        parcel.writeString(mJob)
        parcel.writeString(mJobName)
        parcel.writeValue(birthdayYear)
        parcel.writeValue(birthdayMonth)
        parcel.writeValue(birthdayDay)
        parcel.writeString(mAddress)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MemberInfoEntity> {
        override fun createFromParcel(parcel: Parcel): MemberInfoEntity {
            return MemberInfoEntity(parcel)
        }

        override fun newArray(size: Int): Array<MemberInfoEntity?> {
            return arrayOfNulls(size)
        }
    }
}