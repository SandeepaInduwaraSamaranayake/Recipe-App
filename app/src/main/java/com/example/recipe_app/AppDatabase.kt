package com.example.recipe_app

import androidx.room.Database
import androidx.room.RoomDatabase

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
 * create a database for storing and accessing Meal objects using Room, which is a part of the Android Jetpack library.
 * creating an abstract class named AppDatabase which extends the RoomDatabase class and is annotated with @Database.
 * The @Database annotation specifies that this class represents a database and takes two parameters:
 *
 * @param entities  an array of entities that belong to this database. In this case, it specifies that the Meal class is an entity belonging to this database.
 * @param version an integer value that specifies the version number of the database.
 *
 * This class also declares an abstract function mealDao() that returns an instance of MealDao interface.
 * The MealDao interface is not shown in this code, but it is presumably used to interact with the Meal entity in the database.
 */
@Database(entities = [Meal::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun mealDao(): MealDao
}
