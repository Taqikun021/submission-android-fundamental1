package com.negate.myapplication

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val username: String?,
    val name: String?,
    val location: String?,
    val repository: String?,
    val company: String?,
    val followers: String?,
    val following: String?,
    val avatar: Int?
) : Parcelable
