package com.jpmorgan.album.network

import java.io.IOException

class NoConnectivityException : IOException() {
    override fun getLocalizedMessage(): String? {
        return "No Internet Connection"
    }
}