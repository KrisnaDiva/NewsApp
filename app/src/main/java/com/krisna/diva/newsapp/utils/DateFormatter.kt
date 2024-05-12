package com.krisna.diva.newsapp.utils

import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {
    fun convertDate(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val date = inputFormat.parse(inputDate)
        return if (date != null) {
            outputFormat.format(date)
        } else {
            ""
        }
    }
}