package com.example.wheelshare_client.util

import kotlinx.datetime.toLocalDateTime

fun toLocalDate(date: String): String {

    val local = date.toLocalDateTime()

    return "${local.year}-${local.month}-${local.dayOfMonth} "
}

val months =
    listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")


fun toLocalDateCommaSeparated(date: String): String{
    val local = date.toLocalDateTime()
    return "${local.dayOfMonth} ${months[local.month.ordinal]}, ${local.year} "
}