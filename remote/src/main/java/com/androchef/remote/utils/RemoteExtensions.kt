package com.androchef.remote.utils

import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

internal fun String.takeOnlyValidCharacters(): String {
    return if (this.isNotEmpty())
        this.replace("[^a-zA-Z0-9]", " ").replace("\\s+", "")
    else
        this
}

internal fun Throwable.transform(): Exception {
    return when (this) {
        is UnknownHostException,
        is InterruptedIOException,
        is ConnectException -> Exception("You 're currently offline. Please check your network connection and try again.")
        is SSLHandshakeException,
        is SocketTimeoutException -> Exception("We are unable to connect to our servers. Please check your connection and try again.")
        else -> Exception("Something went wrong please try again.")
    }
}