package com.praveen.omdb.utils.network

import java.io.IOException

class NoConnectionException : IOException() {

    // You can send any message whatever you want from here.
    override val message: String
        get() = "No Internet Connection"
}