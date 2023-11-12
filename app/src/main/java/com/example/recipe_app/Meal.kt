package com.example.recipe_app

import  androidx.room.Entity
import androidx.room.PrimaryKey

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
 * class Meal and annotates it with @Entity.
 * The @Entity annotation indicates that this class represents an entity in a database table.
 * The database table is typically created based on the properties of this class.
 *
 * The @PrimaryKey annotation is applied to the idMeal property, indicating that it serves as the
 * primary key of the entity in the database table.
 *
 * this code is used to define a data class that represents a meal entity and can be used in
 * conjunction with Room to store and access meal data in a database.
 */
@Entity
data class Meal(
    @PrimaryKey val idMeal: Int,
    val strMeal: String?,
    val strDrinkAlternate: String?,
    val strCategory: String?,
    val strArea: String?,
    val strInstructions: String?,
    val strMealThumb: String?,
    val strTags: String?,
    val strYoutube: String?,
    val strIngredient1: String?,
    val strIngredient2: String?,
    val strIngredient3: String?,
    val strIngredient4: String?,
    val strIngredient5: String?,
    val strIngredient6: String?,
    val strIngredient7: String?,
    val strIngredient8: String?,
    val strIngredient9: String?,
    val strIngredient10: String?,
    val strIngredient11: String?,
    val strIngredient12: String?,
    val strIngredient13: String?,
    val strIngredient14: String?,
    val strIngredient15: String?,
    val strIngredient16: String?,
    val strIngredient17: String?,
    val strIngredient18: String?,
    val strIngredient19: String?,
    val strIngredient20: String?,
    val strMeasure1: String?,
    val strMeasure2: String?,
    val strMeasure3: String?,
    val strMeasure4: String?,
    val strMeasure5: String?,
    val strMeasure6: String?,
    val strMeasure7: String?,
    val strMeasure8: String?,
    val strMeasure9: String?,
    val strMeasure10: String?,
    val strMeasure11: String?,
    val strMeasure12: String?,
    val strMeasure13: String?,
    val strMeasure14: String?,
    val strMeasure15: String?,
    val strMeasure16: String?,
    val strMeasure17: String?,
    val strMeasure18: String?,
    val strMeasure19: String?,
    val strMeasure20: String?,
    val strSource: String?,
    val strImageSource: String?,
    val strCreativeCommonsConfirmed: String?,
    val dateModified: String?,
    )

{
    /**
     * getIngredient that takes an integer parameter index and returns a string representing the
     * ingredient corresponding to that index.
     *
     * @param index takes an integer parameter
     *
     * The function uses a when statement to return the ingredient associated with the given index.
     * It checks if the index matches any of the numbered ingredient properties in the Meal class
     * (strIngredient1, strIngredient2, etc.) and returns the corresponding value.
     *
     */
    fun getIngredient(index: Int): String? {
        return when (index) {
            1 -> strIngredient1
            2 -> strIngredient2
            3 -> strIngredient3
            4 -> strIngredient4
            5 -> strIngredient5
            6 -> strIngredient6
            7 -> strIngredient7
            8 -> strIngredient8
            9 -> strIngredient9
            10 -> strIngredient10
            11 -> strIngredient11
            12 -> strIngredient12
            13 -> strIngredient13
            14 -> strIngredient14
            15 -> strIngredient15
            16 -> strIngredient16
            17 -> strIngredient17
            18 -> strIngredient18
            19 -> strIngredient19
            20 -> strIngredient20
            else -> null
        }
    }

    /**
     * getMeasurement that takes an integer parameter i and returns a string representing the
     * measurement corresponding to that index.
     *
     * used to retrieve the measurements for a specific meal entity, given its index in the list of
     * ingredients for that meal. The measurements are typically used in conjunction with the ingredients
     * to provide the complete list of ingredients for a meal.
     *
     * @param i an takes an integer parameter
     *
     * The function uses a when statement to return the measurement associated with the given index.
     * It checks if the index matches any of the numbered measurement properties in the Meal class
     * (strMeasure1, strMeasure2, etc.) and returns the corresponding value.
     *
     * If the index is not found in the range of 1 to 20, the function returns null.
     */
    fun getMeasurement(i: Int): String? {
        return when (i) {
            1 -> strMeasure1
            2 -> strMeasure2
            3 -> strMeasure3
            4 -> strMeasure4
            5 -> strMeasure5
            6 -> strMeasure6
            7 -> strMeasure7
            8 -> strMeasure8
            9 -> strMeasure9
            10 -> strMeasure10
            11 -> strMeasure11
            12 -> strMeasure12
            13 -> strMeasure13
            14 -> strMeasure14
            15 -> strMeasure15
            16 -> strMeasure16
            17 -> strMeasure17
            18 -> strMeasure18
            19 -> strMeasure19
            20 -> strMeasure20
            else -> null
        }
    }
}






