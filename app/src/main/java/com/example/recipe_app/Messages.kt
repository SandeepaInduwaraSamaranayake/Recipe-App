package com.example.recipe_app

import android.content.Context
import androidx.appcompat.app.AlertDialog

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
 * The Messages class is defined as an object rather than a regular class because it's intended
 * to be used as a utility class that provides a single instance of the class that can be shared
 * throughout the application.
 *
 * When a class is defined as an object in Kotlin, it becomes a singleton object. This means that
 * only one instance of the class can be created, and it's created lazily when the object is first
 * referenced in the application.
 *
 * By defining Messages as an object, we can access its methods directly without creating an
 * instance of the class. This is because the methods defined in an object class are implicitly
 * declared as static methods, which means they can be called using the class name without creating
 * an instance of the class.
 */

/**
 * A singleton object to display alert dialog messages.
 */
object Messages
{
    /**
     * Shows an error message with a title, message, and stack trace.
     * @param title the title of the alert dialog
     * @param message the message to display in the alert dialog
     * @param stackTrace the stack trace to include in the error message
     * @param context the context of the activity or fragment displaying the alert dialog
     */
    fun showError(title: String, message: String, stackTrace: String, context: Context)
    {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setMessage("$message Error: $stackTrace")
        alertDialogBuilder.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    /**
     * Shows a success message with a title and message.
     * @param title the title of the alert dialog.
     * @param message the message to display in the alert dialog.
     * @param context the context of the activity or fragment displaying the alert dialog.
     */
    fun showSuccess(title: String, message: String, context: Context)
    {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}