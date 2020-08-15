package com.androchef.remote.utils

internal fun String.takeOnlyValidCharacters(): String {
    return if (this.isNotEmpty())
        this.replace("[^a-zA-Z0-9]", " ").replace("\\s+", "")
    else
        this
}