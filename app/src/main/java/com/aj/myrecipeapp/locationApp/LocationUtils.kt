package com.aj.myrecipeapp.locationApp

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.aj.myrecipeapp.Manifest

class LocationUtils(val context: Context) {

    fun hasLocationPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
                &&
                ContextCompat.checkSelfPermission(
                    context,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }
}