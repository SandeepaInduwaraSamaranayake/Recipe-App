package com.example.recipe_app

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

/**
 * @author Sandeepa Induwara Samaranayake
 * Email - sandeepa.20210302@iit.ac.lk
 * IIT ID - 20210302
 * UOW ID - w1867067
 * Mobile Application Development Coursework II
 * L5 Semester 2
 * Link to demonstration video - https://drive.google.com/drive/folders/1VWpAeftr-887FDzSGDSJAB0ZthNivF2J?usp=sharing
 */

/**
 * The NetworkUtils class is defined as an object rather than a regular class because it's intended
 * to be used as a utility class that provides a single instance of the class that can be shared
 * throughout the application.
 *
 * When a class is defined as an object in Kotlin, it becomes a singleton object. This means that
 * only one instance of the class can be created, and it's created lazily when the object is first
 * referenced in the application.
 *
 * By defining NetworkUtils as an object, we can access its methods directly without creating an
 * instance of the class. This is because the methods defined in an object class are implicitly
 * declared as static methods, which means they can be called using the class name without creating
 * an instance of the class.
 */

/**
 *  This object contains utility functions for network related operations.
 */

object NetworkUtils
{
    /**
     * this method will check whether internet connection is available or not.
     * if network connection is available, return true.
     * if network connection is not available, show an error message, and return false.
     *
     * @param context The context of the calling activity or application.
     * @return True if a network connection is available, false otherwise.
     */
    fun checkNetworkConnectionIsAvailable(context: Context): Boolean
    {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        return if ( networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) )
        {
            true
        }
        else
        {
            Messages.showError("No Internet Connection", "Please check your internet connection and try again.", "Network Connectivity Failure", context)
            false
        }
    }

    /**
     *  This method loads an image from the given URL.
     *  @param url The URL of the image to be loaded.
     *  @return A Bitmap object containing the loaded image.
     */
    suspend fun loadImage(url: String): Bitmap
    {
        val defaultUrl =
            "https://www.refrigeratedfrozenfood.com/ext/resources/NEW_RD_Website/DefaultImages/default-pasta.jpg?1430942591"
        return withContext(Dispatchers.IO)
        {
            try
            {
                // Download the image from the URL
                val connection = URL(url).openConnection() as HttpURLConnection
                connection.doInput = true

                connection.connect()
                val inputStream = connection.inputStream

                // Decode the input stream into a bitmap
                BitmapFactory.decodeStream(inputStream)
            }
            catch (e: Exception)
            {
                e.printStackTrace()
                // If the image is not available, return the default bitmap
                val defaultConnection =
                    URL(defaultUrl).openConnection() as HttpURLConnection
                defaultConnection.doInput = true
                defaultConnection.connect()
                val defaultInputStream = defaultConnection.inputStream
                BitmapFactory.decodeStream(defaultInputStream)
            }
        }
    }
}
