package com.example.recipe_app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.*
import java.util.ArrayList
import androidx.activity.viewModels

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
 * The SearchForMealsActivity class is responsible for handling the user's input and displaying the results on the screen.
 * It contains a RecyclerView that displays a list of meals that match the user's search criteria.
 * This class uses a ViewModel to manage the data and the state of the app.
 */
class SearchForMealsActivity: AppCompatActivity()
{
    private lateinit var recyclerView: RecyclerView
    private lateinit var pairs: ArrayList<ImageDescriptionPair>
    private val viewModel: SearchForMealsViewModel by viewModels()

    /**
     * This method is called when the activity is created. It sets the content view,
     * gets references to the text fields and buttons, restores any saved data,
     * sets the RecyclerView's layout manager and adapter, and sets the onClickListener for the search button.
     */
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_for_meals)

        // get text fields and buttons as kotlin objects.
        val txtSearch = findViewById<EditText>(R.id.txt_search)
        val btnSearch = findViewById<Button>(R.id.btn_search)

        // get recyclerView as an object
        recyclerView = findViewById(R.id.recycler_view)

        // restore previous data.

        // set recycler view layout manager
        recyclerView.layoutManager = LinearLayoutManager(this@SearchForMealsActivity)

        pairs = viewModel.pairs ?: ArrayList<ImageDescriptionPair>()
        // set recycler view adapter
        recyclerView.adapter = ImageDescriptionAdapter(pairs)


        // setting onClickListener to search btn.
        btnSearch.setOnClickListener {
            // checking whether the network connection is available or not.
            // internet e the URI.
            // all the data will be loaded from the database, but images will be loaded from the
            // internet using the retrieved uri from the database.
            if ( NetworkUtils.checkNetworkConnectionIsAvailable(this) )
            {
                // get the user's input.
                val usrInput = txtSearch.text.toString()

                // validate the user input. empty user inputs will fail the validation.
                if (Validation.validateUserInput(usrInput, this)) {
                    // can't perform database in/out operations on the main thread.
                    // so starting a new coroutine and run the db operation there.
                    // Connect the main code with a new coroutine scope using a
                    // runBlocking block of code.
                    runBlocking {
                        //  Start a new coroutine with a launch block of code.
                        launch {
                            // Run the code of the coroutine in a new thread different than the main.
                            withContext(Dispatchers.IO)
                            {
                                try {
                                    // get the passed database id through intent.
                                    val dbId = intent.getStringExtra("db_id")
                                    // make the connection to the database.
                                    val db = Room.databaseBuilder(
                                        this@SearchForMealsActivity,
                                        AppDatabase::class.java,
                                        dbId
                                    ).build()
                                    val mealDao = db.mealDao()
                                    // search from database.
                                    val meals = mealDao.searchMealsByNameOrIngredient(usrInput)

                                    runOnUiThread {
                                        // runOnUiThread block is modified to launch a coroutine that runs on the UI
                                        // thread using the GlobalScope.launch(Dispatchers.Main) function. The
                                        // withContext(Dispatchers.Default) function specifies that the showRecipesInRecyclerView
                                        // function should run on a separate thread to avoid blocking the UI thread.
                                        GlobalScope.launch(Dispatchers.Main) {
                                            val mealList: List<Meal> = meals

                                            // set layout manager to recyclerView.
                                            recyclerView.layoutManager =
                                                LinearLayoutManager(this@SearchForMealsActivity)

                                            // Clear the pairs list before adding new data to it
                                            pairs.clear()

                                            //  The withContext function is used to run the showRecipesInRecyclerView
                                            //  function on the default dispatcher, which is a separate thread. The
                                            //  result of the showRecipesInRecyclerView function is returned to the
                                            //  pairs variable, which is then used to create the adapter for the RecyclerView.
                                            pairs = withContext(Dispatchers.Default) {
                                                RecipeView.showRecipesInRecyclerView(mealList, pairs)
                                            }

                                            // creating adapter for recyclerView.
                                            val adapter = ImageDescriptionAdapter(pairs)
                                            // setting adapter for recyclerView.
                                            recyclerView.adapter = adapter
                                            adapter.notifyDataSetChanged()
                                        }
                                    }
                                } catch (e: Exception) {
                                    // show an error if search process is failed.
                                    Messages.showError(
                                        "Error", "Error Occurred while Retrieving Data From Database",
                                        e.message.toString(), this@SearchForMealsActivity
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * override onSaveInstanceState method.
     */
    override fun onSaveInstanceState(outState: Bundle)
    {
        super.onSaveInstanceState(outState)
        viewModel.pairs = pairs
    }
}