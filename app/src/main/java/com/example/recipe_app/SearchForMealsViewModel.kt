package com.example.recipe_app

import androidx.lifecycle.ViewModel

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
 * The ViewModel for the SearchForMealsActivity. This class is responsible for storing the state of the activity,
 * such as the list of ImageDescriptionPairs.
 */
class SearchForMealsViewModel: ViewModel()
{
    var pairs: ArrayList<ImageDescriptionPair>? = null
}