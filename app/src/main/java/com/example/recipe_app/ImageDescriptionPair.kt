package com.example.recipe_app

import android.graphics.Bitmap

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
 * A data class called ImageDescriptionPair that represents an image-description pair.
 * @param image A Bitmap object representing an image.
 * @param description A String representing a description of the image.
 * The ImageDescriptionPair class is a simple data class that holds an image and its corresponding description.
 */
data class ImageDescriptionPair(
    val image: Bitmap,
    val description: String )
