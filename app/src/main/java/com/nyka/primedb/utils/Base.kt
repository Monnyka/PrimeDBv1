package com.nyka.primedb.utils

import java.text.SimpleDateFormat
import java.util.*

class Base() {

    fun dateFormatting(date: String): String {
        return if (date.isNullOrBlank()) {
            ("N/A")
        } else {
            val pattern = "dd MMMM, yyyy"
            val simpleDateFormat = SimpleDateFormat(pattern)
            return simpleDateFormat.format(Date())
        }
    }

    fun yearFormatting(date: String): String? {
        return if (date.isNullOrBlank()) {
            ("N/A")
        } else {
            val pattern = "yyyy"
            val simpleDateFormat = SimpleDateFormat(pattern)
            return simpleDateFormat.format(Date())
        }
    }
}