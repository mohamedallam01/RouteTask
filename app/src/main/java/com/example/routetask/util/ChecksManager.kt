package com.example.weatherwise.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities


private const val REQUEST_CODE_DRAW_OVER_OTHER_APPS = 123
object ChecksManager {


    fun checkConnection(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        return capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(
            NetworkCapabilities.TRANSPORT_CELLULAR
        ))
    }




}