package com.vahagn.androidmidhomework13

import java.util.*

enum class PhoneType {
    mobile, city
}

data class Contact(val name: String, val phoneNumber: String, val group: String, val lastCall: Date?, val type: PhoneType)