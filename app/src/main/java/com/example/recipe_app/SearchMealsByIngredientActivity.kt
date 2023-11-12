package com.example.recipe_app

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONObject
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

var apiKey = 1

/**
 * This activity allows the user to search for meal recipes by ingredient. It uses the searchRecipesByIngredient()
 * function to retrieve the list of meal recipes that include the specified ingredient. If there is a network
 * connection, the retrieved recipes can be saved to the database. The user interface consists of an EditText field
 * for the ingredient, a button to retrieve the recipes, and a button to save the retrieved recipes to the database.
 * The retrieved recipes are displayed in a TextView.
 *
 * @constructor Creates an instance of the SearchMealsByIngredientActivity.
 */
class SearchMealsByIngredientActivity: AppCompatActivity()
{
    private lateinit var txtIngredient: EditText
    private lateinit var btnRetrieveMeals: Button
    private lateinit var btnSaveToDb: Button
    private lateinit var txtMealDetails:TextView

    /**
     * Initializes the activity's UI elements, sets onClickListeners for the buttons, and restores any saved instance
     * state.
     *
     * @param savedInstanceState The saved instance state.
     */
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_meals_by_ingredient)

        // get txt fields and buttons as kotlin objects.
        txtIngredient = findViewById<EditText>(R.id.txt_ingredient)
        btnRetrieveMeals = findViewById<Button>(R.id.btn_retrieve_meals)
        btnSaveToDb = findViewById<Button>(R.id.btn_save_to_db)
        txtMealDetails = findViewById<TextView>(R.id.txt_meal_details)

        // if the savedInstanceState is not null, restore data.
        if (savedInstanceState != null)
        {
            // set the retrieved text to textview.
            txtMealDetails.text = savedInstanceState.getString("textViewMealsText", "")
            // setting visibility to true.
            txtMealDetails.visibility = View.VISIBLE
        }

        // setting onClickListener to retrieve meals btn.
        btnRetrieveMeals.setOnClickListener {

            // checking whether the network connection is available or not.
            if ( NetworkUtils.checkNetworkConnectionIsAvailable(this) )
            {
                // get the user's input ingredient.
                val inputIngredient = txtIngredient.text.toString()

                // validate the user input. empty user inputs will fail the validation.
                if ( Validation.validateUserInput(inputIngredient, this) )
                {
                    // can't perform network operations on the main thread.
                    // so starting a new coroutine and run the network operation there.
                    // Connect the main code with a new coroutine scope using a
                    // runBlocking block of code.
                    runBlocking {
                        //  Start a new coroutine with a launch block of code.
                        launch {
                            // Run the code of the coroutine in a new thread different than the main.
                            withContext(Dispatchers.IO) {
                                // perform the network operation.
                                val mealList: List<Meal> =
                                    searchRecipesByIngredient(inputIngredient)

                                // create a stringBuilder object to hold the string, that we need to show in the TextView.
                                val stbMealRecipes: StringBuilder = RecipeView.showRecipesInTextView(mealList)

                                // can't access or update UI elements from a thread other than the main(UI) thread.
                                // In android, only the main thread is allowed to make changes to the UI.
                                // if returned stringBuilder object is not empty, then show the content.
                                // if a blank stringBuilder object is returned, show error message.
                                runOnUiThread {

                                    // show the txtView.
                                    txtMealDetails.visibility = View.VISIBLE

                                    // show content.
                                    if (stbMealRecipes.isNotEmpty())
                                    {
                                        // set stringBuilder object text to textview
                                        txtMealDetails.text = stbMealRecipes.toString()
                                    }
                                    else
                                    {
                                        // if stringBuilder object is empty, show a default message.
                                        txtMealDetails.text = getString(R.string.cannot_find_matching_meals)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // setting onClickListener to save to DB btn.
        btnSaveToDb.setOnClickListener {

            // checking whether the network connection is available or not.
            if ( NetworkUtils.checkNetworkConnectionIsAvailable(this) )
            {
                // get the user's input ingredient.
                val inputIngredient = txtIngredient.text.toString()

                // validate the user input. empty user inputs will fail the validation.
                if ( Validation.validateUserInput(inputIngredient, this) )
                {
                    // can't perform network operations on the main thread.
                    // so starting a new coroutine and run the network operation there.
                    // Connect the main code with a new coroutine scope using a
                    // runBlocking block of code.
                    runBlocking {
                        //  Start a new coroutine with a launch block of code.
                        launch {
                            // Run the code of the coroutine in a new thread different than the main.
                            withContext(Dispatchers.IO) {
                                // perform the network operation.
                                val mealList: List<Meal> = searchRecipesByIngredient(inputIngredient)

                                // save retrieved data to database, if the retrieved data list is not empty.
                                if(mealList.isNotEmpty())
                                {
                                    saveToDB(mealList)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Overrides the onSaveInstanceState function to save the text view's text to the bundle.
     * @param outState The bundle that the text view's text is saved to.
     */
    override fun onSaveInstanceState(outState: Bundle)
    {
        super.onSaveInstanceState(outState)
        // put textView text to bundle.
        outState.putString("textViewMealsText", txtMealDetails.text.toString())
    }

    /**
     * Searches for recipes related to the given ingredient and adds them to a list.
     * @param ingredient The ingredient to search for.
     * @return The list of meals that contain the given ingredient.
     */
    private fun searchRecipesByIngredient(ingredient: String): List<Meal>
    {
        // create a mutable list object which can hold Meal instances.
        var recipes = mutableListOf<Meal>()

        // using a try-catch block, because errors can be thrown in this particular block of code.
        try {
            // can't access the whole details in one step.
            // first we need to get the all meals summery by filtering the ingredients.
            // after finding what are the meals include that ingredient, we can access full detailed
            // description using it's meal Id.

            // create the api url according to the given ingredient and retrieved api key.
            val urlSummery =
                URL("https://www.themealdb.com/api/json/v1/$apiKey/filter.php?i=$ingredient")
            // read the api returned output.
            val jsonStringSummery = urlSummery.readText()
            // convert the returned output to json object
            val jsonSummery = JSONObject(jsonStringSummery)
            // get json array named meals.
            val mealsSummery = jsonSummery.getJSONArray("meals")

            for (i in 0 until mealsSummery.length()) {
                // get the json object by object from the mealSummery json array.
                val mealDetails = mealsSummery.getJSONObject(i)
                // get that meal's meal id.
                val mealId = mealDetails.getInt("idMeal")
                // create another request url to request the detailed description about the particular
                // meal using the meal id.
                val url = URL("https://www.themealdb.com/api/json/v1/$apiKey/lookup.php?i=$mealId")

                // read the api returned output.
                val jsonString = url.readText()
                // convert the returned output to json object
                val json = JSONObject(jsonString)
                // get json array named meals.
                val meals = json.getJSONArray("meals")

                for (j in 0 until meals.length()) {
                    // getting meal by meal from meals list.
                    val meal = meals.getJSONObject(j)

                    // getting all data and adding all retrieved Meal data to mutable list.
                    recipes = DataReceiver.getJsonData(meal, recipes)
                }
            }
        }
        catch (e:Exception)
        {
            // this method ( searchRecipesByIngredient() ) is invoked inside a coroutine(another lightweight
            // thread) to allow it to use the network.
            // so we need to switch to ui(main) thread before show an error message Dialog.
            runOnUiThread {
                // if an error occurred, show error message inside a alert dialog box.
                Messages.showError("Error", "An Error Occurred While Retrieving the Data. ", e.message.toString(), this)
            }
        }
        // return mutable list.
        return recipes
    }

    /**
     * this method will save the retrieved data to the database.
     * @param mealList the list of meals that will be saved.
     */
    private suspend fun saveToDB(mealList: List<Meal>)
    {
        try
        {
            // get the passed database id through intent.
            val dbId = intent.getStringExtra("db_id")
            // make the connection to the database.
            val db = Room.databaseBuilder(this, AppDatabase::class.java, dbId).build()
            val mealDao = db.mealDao()
            // inserting data to database.
            for (i in mealList)
            {
                mealDao.insertMeals(i)
            }
            // this method ( saveToDB() ) is invoked inside a coroutine( another lightweight
            // thread ) to allow it to use the network.
            // so we need to switch to ui(main) thread before show a success message Dialog.
            runOnUiThread{
                Messages.showSuccess("Data Saved", "All Retrieved Data Saved To The Database", this)
            }
        }
        catch (e: Exception)
        {
            // this method ( saveToDB() ) is invoked inside a coroutine( another lightweight
            // thread ) to allow it to use the network.
            // so we need to switch to ui(main) thread before show an error message Dialog.
            runOnUiThread {
                Messages.showError("Error", "Error In Saving Data to Database. Try Again Later.", e.message.toString(), this)
            }
        }
    }
}