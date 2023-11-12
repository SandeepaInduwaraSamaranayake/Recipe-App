package com.example.recipe_app

import org.json.JSONObject

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
 * "DataReceiver" with a single method called "getJsonData".
 */
object DataReceiver {

    /**
     * getJsonData method specifically is responsible for extracting and storing data for a single meal.
     *
     * The method takes two arguments:
     * @param meal  a JSONObject representing data for a single meal
     * @param recipes MutableList of Meal objects representing a collection of meals.
     *
     * The purpose of the method is to extract data from the JSONObject and add it to the list of Meal objects.
     *
     * it creates a new Meal object using the data from the JSONObject and adds it to the list using the
     * "add" method. The method then returns the updated list.
     *
     * The Meal object has many properties, including idMeal, strMeal, strCategory....
     * The method extracts values for each property from the JSONObject using methods like "getInt"
     * and "getString" and passes them as arguments to the constructor of the Meal object.
     *
     */
    fun getJsonData(meal: JSONObject, recipes: MutableList<Meal>): MutableList<Meal>
    {
        // adding json object data to recipes Mutable list.
        recipes.add(
            Meal(
                meal.getInt("idMeal"),
                meal.getString("strMeal"),
                meal.getString("strDrinkAlternate"),
                meal.getString("strCategory"),
                meal.getString("strArea"),
                meal.getString("strInstructions"),
                meal.getString("strMealThumb"),
                meal.getString("strTags"),
                meal.getString("strYoutube"),
                meal.getString("strIngredient1"),
                meal.getString("strIngredient2"),
                meal.getString("strIngredient3"),
                meal.getString("strIngredient4"),
                meal.getString("strIngredient5"),
                meal.getString("strIngredient6"),
                meal.getString("strIngredient7"),
                meal.getString("strIngredient8"),
                meal.getString("strIngredient9"),
                meal.getString("strIngredient10"),
                meal.getString("strIngredient11"),
                meal.getString("strIngredient12"),
                meal.getString("strIngredient13"),
                meal.getString("strIngredient14"),
                meal.getString("strIngredient15"),
                meal.getString("strIngredient16"),
                meal.getString("strIngredient17"),
                meal.getString("strIngredient18"),
                meal.getString("strIngredient19"),
                meal.getString("strIngredient20"),
                meal.getString("strMeasure1"),
                meal.getString("strMeasure2"),
                meal.getString("strMeasure3"),
                meal.getString("strMeasure4"),
                meal.getString("strMeasure5"),
                meal.getString("strMeasure6"),
                meal.getString("strMeasure7"),
                meal.getString("strMeasure8"),
                meal.getString("strMeasure9"),
                meal.getString("strMeasure10"),
                meal.getString("strMeasure11"),
                meal.getString("strMeasure12"),
                meal.getString("strMeasure13"),
                meal.getString("strMeasure14"),
                meal.getString("strMeasure15"),
                meal.getString("strMeasure16"),
                meal.getString("strMeasure17"),
                meal.getString("strMeasure18"),
                meal.getString("strMeasure19"),
                meal.getString("strMeasure20"),
                meal.getString("strSource"),
                meal.getString("strImageSource"),
                meal.getString("strCreativeCommonsConfirmed"),
                meal.getString("dateModified")
            )
        )
        return recipes
    }
}