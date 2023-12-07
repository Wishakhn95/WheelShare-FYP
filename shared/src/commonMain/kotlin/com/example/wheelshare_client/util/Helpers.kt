package com.example.wheelshare_client.util

import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

fun isPhoneValid(userPhoneNumber: String): Boolean {
    return ((userPhoneNumber.startsWith("030") || userPhoneNumber.startsWith("031") || userPhoneNumber.startsWith(
        "032"
    ) || userPhoneNumber.startsWith("034") || userPhoneNumber.startsWith("035") || userPhoneNumber.startsWith(
        "033"
    ) || userPhoneNumber.startsWith("+92"))) && userPhoneNumber.matches("^(03|\\+923)\\d{9}\$".toRegex())
}

// pretty format number using only kotlin
fun prettyCount(count: Long): Pair<Double, String> {
    val array = arrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
    val value = floor(log10(count.toDouble())).toInt()
    val base = value / 3
    return if (value >= 3 && base < array.size) {
        Pair((count / 10.0.pow((base * 3).toDouble())), array[base].toString())
    } else {
        Pair(count.toDouble(), "")
    }
}

fun isEnglishInput(input: String): Boolean {
    return input.all { it.isLetter() && it.isEnglishLetter() }
}

fun Char.isEnglishLetter(): Boolean {
    return this in 'a'..'z' || this in 'A'..'Z'
}

fun convertNumberToCommaSeparated(number: Double?): String {
    val value = number ?: 0.0
    val thousandsSeparator = ","
    val myNumberString = value.toInt().toString()
    val numberLength = myNumberString.length
    var howManySeparators = floor((numberLength - 1.0) / 3).toInt()
    var formattedString = myNumberString.substring(0, (numberLength - (howManySeparators * 3)))
    while (howManySeparators > 0) {
        formattedString = formattedString + thousandsSeparator + myNumberString.substring(
            (numberLength - (howManySeparators * 3)), (numberLength - ((howManySeparators - 1) * 3))
        )
        howManySeparators -= 1;
    }

    return formattedString
}


fun convertNumberToCommaSeparated(number: Int?): String {
    val value = number ?: 0
    val thousandsSeparator = ","
    val myNumberString = value.toString()
    val numberLength = myNumberString.length
    var howManySeparators = floor((numberLength - 1.0) / 3).toInt()
    var formattedString = myNumberString.substring(0, (numberLength - (howManySeparators * 3)))
    while (howManySeparators > 0) {
        formattedString = formattedString + thousandsSeparator + myNumberString.substring(
            (numberLength - (howManySeparators * 3)), (numberLength - ((howManySeparators - 1) * 3))
        )
        howManySeparators -= 1;
    }

    return formattedString
}
