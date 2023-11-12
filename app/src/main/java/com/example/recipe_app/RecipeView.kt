package com.example.recipe_app

import java.util.ArrayList

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
 * The RecipeView class is defined as an object rather than a regular class because it's intended
 * to be used as a utility class that provides a single instance of the class that can be shared
 * throughout the application.
 *
 * When a class is defined as an object in Kotlin, it becomes a singleton object. This means that
 * only one instance of the class can be created, and it's created lazily when the object is first
 * referenced in the application.
 *
 * By defining RecipeView as an object, we can access its methods directly without creating an
 * instance of the class. This is because the methods defined in an object class are implicitly
 * declared as static methods, which means they can be called using the class name without creating
 * an instance of the class.
 */

/**
 * this method will create the string that will be shown in the textView.
 * The RecipeView object contains functions that are responsible for displaying recipes either in a textview or in a recyclerview.
 */

object RecipeView
{
    /**
     * This function receives a list of meals and returns a StringBuilder object containing all of the meal
     * information as a string to be displayed in a textview.
     * @param mealList The list of meals to display in the textview.
     * @return A StringBuilder object containing all of the meal information as a string.
     */
    fun showRecipesInTextView(mealList: List<Meal>): StringBuilder
    {
        // create a stringBuilder object to hold recipes.
        val stbMealRecipes = StringBuilder()

        // get meal by meal and append that all meals to stringBuilder object as strings.
        for(meal in mealList)
        {
            val ingredients = mutableListOf<String>()
            val measurements = mutableListOf<String>()

            for ( i in 1..20 )
            {
                val ingredient = meal.getIngredient(i)
                val measurement = meal.getMeasurement(i)

                if (Validation.validate(ingredient) && ingredient != null)
                    ingredients.add(ingredient)

                if(Validation.validate(measurement) && measurement != null)
                    measurements.add(measurement)
            }

            with(meal)
            {
                stbMealRecipes.append("Meal:$strMeal\n")
                stbMealRecipes.append("DrinkAlternate:$strDrinkAlternate\n")
                stbMealRecipes.append("Category:$strCategory\n")
                stbMealRecipes.append("Area:$strArea\n")
                stbMealRecipes.append("Instructions:$strInstructions\n")
                stbMealRecipes.append("Tags:$strTags\n")
                stbMealRecipes.append("Youtube:$strYoutube\n")

                for (i in ingredients.indices)
                {
                    stbMealRecipes.append("Ingredient${i + 1}:${ingredients[i]}\n")
                }

                for (i in measurements.indices)
                {
                    stbMealRecipes.append("Measure${i + 1}:${measurements[i]}\n")
                }
                stbMealRecipes.append("\n\n")
            }
        }
        // return the stringBuilder object.
        return stbMealRecipes
    }

    /**
     * The showRecipesInRecyclerView function is declared as a suspend function, so it can be called from within a coroutine.
     * This function receives a list of meals and a list of ImageDescriptionPairs, downloads the meal images and appends them
     * to the ImageDescriptionPairs list.
     * @param mealList The list of meals to be displayed in the recyclerview.
     * @param pairs The list of ImageDescriptionPairs.
     * @return The updated list of ImageDescriptionPairs.
     */
    suspend fun showRecipesInRecyclerView(mealList: List<Meal>, pairs: ArrayList<ImageDescriptionPair>): ArrayList<ImageDescriptionPair>
    {
        for (meal in mealList)
        {
            val ingredients = mutableListOf<String>()
            val measurements = mutableListOf<String>()

            for (i in 1..20)
            {
                val ingredient = meal.getIngredient(i)
                val measurement = meal.getMeasurement(i)

                if (Validation.validate(ingredient) && ingredient != null)
                    ingredients.add(ingredient)

                if(Validation.validate(measurement) && measurement != null)
                    measurements.add(measurement)
            }

            val recipe = StringBuilder()
            with(meal)
            {
                recipe.append("Meal:$strMeal\n")
                recipe.append("DrinkAlternate:$strDrinkAlternate\n")
                recipe.append("Category:$strCategory\n")
                recipe.append("Area:$strArea\n")
                recipe.append("Instructions:$strInstructions\n")
                recipe.append("Tags:$strTags\n")
                recipe.append("Youtube:$strYoutube\n")

                for (i in ingredients.indices)
                {
                    recipe.append("Ingredient${i + 1}:${ingredients[i]}\n")
                }

                for (i in measurements.indices)
                {
                    recipe.append("Measure${i + 1}:${measurements[i]}\n")
                }
                recipe.append("\n\n")
            }
            pairs.add(ImageDescriptionPair( NetworkUtils.loadImage(meal.strMealThumb!!), recipe.toString()) )
        }
        return pairs
    }
}