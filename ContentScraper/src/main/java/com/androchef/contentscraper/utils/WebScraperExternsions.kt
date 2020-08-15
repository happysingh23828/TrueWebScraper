package com.androchef.contentscraper.utils

internal fun String.addWithComma(string: String): String {
    return if (this.isEmpty()) {
        string
    } else {
        "$this, $string"
    }
}

internal fun String.addWithComma(char: Char): String {
    return if (this.isEmpty()) {
        char.toString()
    } else {
        "$this, $char"
    }
}

internal fun String?.isValid(): Boolean {
    return this != null && this.isNotEmpty() && this.isNotBlank()
}

internal fun String?.isHavingLength(lengthToCHeck: Int): Boolean {
    return this.isValid() && lengthToCHeck != 0 && this!!.length >= lengthToCHeck
}