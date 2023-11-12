package com.example.recipe_app

import android.content.Context

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
 * The Validation class is defined as an object rather than a regular class because it's intended
 * to be used as a utility class that provides a single instance of the class that can be shared
 * throughout the application.
 *
 * When a class is defined as an object in Kotlin, it becomes a singleton object. This means that
 * only one instance of the class can be created, and it's created lazily when the object is first
 * referenced in the application.
 *
 * By defining Validation as an object, we can access its methods directly without creating an
 * instance of the class. This is because the methods defined in an object class are implicitly
 * declared as static methods, which means they can be called using the class name without creating
 * an instance of the class.
 */

/**
 * The Validation object contains methods for validating strings and user input.
 */
object Validation
{
    /**
     * This method validates a string. If the passed string is "null", "", or " ", it returns false.
     *
     * @param str The string to be validated.
     * @return Returns true if the string is not "null", "", or " "; otherwise, returns false.
     */
    fun validate(str: String?): Boolean
    {
        return if(str == "null")
            false
        else !(str == "" || str == " ")
    }

    /**
     * This method validates user input. If the user input is empty, it returns false and displays an error message.
     *
     * @param usrInput The user input to be validated.
     * @param context The context of the application.
     * @return Returns true if the user input is not empty; otherwise, returns false.
     */
    fun validateUserInput(usrInput: String, context: Context): Boolean
    {
        return if( usrInput != "")
        {
            true
        }
        else
        {
            // if user input is not valid, show an error.
            Messages.showError("Enter an Ingredient",
                "Please Enter an Ingredient Before Retrieve Meals.",
                "Empty Ingredient is not valid", context)
            false
        }
    }
}