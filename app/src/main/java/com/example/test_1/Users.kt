package com.example.test_1

import android.os.Parcelable
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.provider.ContactsContract.CommonDataKinds.Website
import kotlinx.android.parcel.Parcelize
import okhttp3.Address

@Parcelize
data class Users(
    val id:Int,
    val name:String,
    val username:String,
    val email:String,
    val address: com.example.test_1.Address,
    val phone: String,
    val website: String,
    val company:Company

):Parcelable

@Parcelize
data class Address(
    val street:String,
    val suite:String,
    val city:String,
    val zipcode:String,
    val geo:Geo
):Parcelable

@Parcelize
data class Geo(
    val lat:String,
    val lng:String
):Parcelable

@Parcelize
data class Company(
    val name:String,
    val catchPhrase:String,
    val bs:String
):Parcelable
