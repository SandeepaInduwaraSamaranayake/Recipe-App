package com.example.recipe_app

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

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
 * Data Access Object (DAO) interface for interacting with the "meal" table in the local database.
 */
@Dao
interface MealDao {

    /**
     * Retrieves all the meals from the "meal" table.
     * @return List of Meal objects.
     */
    @Query("SELECT * FROM meal")
    suspend fun getAll(): List<Meal>

    /**
     * Searches for meals that match the given query in their name or ingredient list.
     * @param query The search query string.
     * @return List of Meal objects that match the search query.
     */
    @Query("SELECT * FROM meal WHERE strMeal LIKE '%' || :query || '%' " +
                          "OR strIngredient1 LIKE '%' || :query || '%' " +
                          "OR strIngredient2 LIKE '%' || :query || '%' " +
                          "OR strIngredient3 LIKE '%' || :query || '%' " +
                          "OR strIngredient4 LIKE '%' || :query || '%' " +
                          "OR strIngredient5 LIKE '%' || :query || '%' " +
                          "OR strIngredient6 LIKE '%' || :query || '%' " +
                          "OR strIngredient7 LIKE '%' || :query || '%' " +
                          "OR strIngredient8 LIKE '%' || :query || '%' " +
                          "OR strIngredient9 LIKE '%' || :query || '%' " +
                          "OR strIngredient10 LIKE '%' || :query || '%' " +
                          "OR strIngredient11 LIKE '%' || :query || '%' " +
                          "OR strIngredient12 LIKE '%' || :query || '%' " +
                          "OR strIngredient13 LIKE '%' || :query || '%' " +
                          "OR strIngredient14 LIKE '%' || :query || '%' " +
                          "OR strIngredient15 LIKE '%' || :query || '%' " +
                          "OR strIngredient16 LIKE '%' || :query || '%' " +
                          "OR strIngredient17 LIKE '%' || :query || '%' " +
                          "OR strIngredient18 LIKE '%' || :query || '%' " +
                          "OR strIngredient19 LIKE '%' || :query || '%' " +
                          "OR strIngredient20 LIKE '%' || :query || '%' " )
    fun searchMealsByNameOrIngredient(query: String): List<Meal>

    /**
     * Inserts one or more Meal objects into the "meal" table. If the meal already exists, it will be replaced with the new one.
     * @param meal The Meal object(s) to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeals(vararg meal: Meal)

    /**
     * Inserts one or more Meal objects into the "meal" table.
     * @param meal The Meal object(s) to insert.
     */
    @Insert
    suspend fun insertAll(vararg meal: Meal)
}
