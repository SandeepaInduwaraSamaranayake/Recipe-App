package com.example.recipe_app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

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
 * This class represents an activity that allows the user to search for meals on a web API.
 * It uses a RecyclerView to display the search results, and allows the user to save the
 * results to the local database for later use.
 * @property recyclerView The RecyclerView used to display the search results.
 * @property pairs An ArrayList of ImageDescriptionPair objects that represents the data to be displayed in the RecyclerView.
 * @property viewModel The SearchForMealsViewModel that provides the data for the activity.
 * @constructor Creates an instance of the SearchMealsWebActivity.
 */
class SearchMealsWebActivity: AppCompatActivity()
{
    private lateinit var recyclerView: RecyclerView
    private lateinit var pairs: ArrayList<ImageDescriptionPair>
    private val viewModel: SearchForMealsViewModel by viewModels()

    /**
     * This method is called when the activity is created.
     * @param savedInstanceState A Bundle containing the activity's previously saved state.
     */
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_for_meals_web)

        // get text fields and buttons as kotlin objects.
        val txtSearch = findViewById<EditText>(R.id.txt_search)
        val btnSearch = findViewById<Button>(R.id.btn_search)

        // get recyclerView as an object
        recyclerView = findViewById(R.id.recycler_view)

        // restore previous data.

        // set recycler view layout manager
        recyclerView.layoutManager = LinearLayoutManager(this@SearchMealsWebActivity)

        pairs = viewModel.pairs ?: ArrayList<ImageDescriptionPair>()
        // set recycler view adapter
        recyclerView.adapter = ImageDescriptionAdapter(pairs)

        btnSearch.setOnClickListener {
            // checking whether the network connection is available or not.
            if ( NetworkUtils.checkNetworkConnectionIsAvailable(this) )
            {
                // get the user's input.
                val usrInput = txtSearch.text.toString()

                // validate the user input. empty user inputs will fail the validation.
                if ( Validation.validateUserInput(usrInput, this) )
                {
                    runOnUiThread{
                        // runOnUiThread block is modified to launch a coroutine that runs on the UI
                        // thread using the GlobalScope.launch(Dispatchers.Main) function. The
                        // withContext(Dispatchers.Default) function specifies that the showRecipesInRecyclerView
                        // function should run on a separate thread to avoid blocking the UI thread.
                        GlobalScope.launch(Dispatchers.Main) {
                            val mealList: List<Meal> = searchMealsByName(usrInput)

                            recyclerView.layoutManager =
                                LinearLayoutManager(this@SearchMealsWebActivity)

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

    /**
     * this method will search the recipes related to the given ingredient and add them to a list.
     * @param mealName the name of the meal, that need to search.
     */
    private fun searchMealsByName(mealName: String): List<Meal>
    {
        // create a mutable list object which can hold Meal instances.
        var recipes = mutableListOf<Meal>()

        // using a try-catch block, because errors can be thrown in this particular block of code.
        try {
            // can't perform network operations on the main thread.
            // so starting a new coroutine and run the network operation there.
            // Connect the main code with a new coroutine scope using a
            // runBlocking block of code.
            runBlocking {
                //  Start a new coroutine with a launch block of code.
                launch {
                    // Run the code of the coroutine in a new thread different than the main.
                    withContext(Dispatchers.IO)
                    {
                        val url = URL("https://www.themealdb.com/api/json/v1/1/search.php?s=$mealName")
                        val conn = url.openConnection() as HttpURLConnection
                        conn.requestMethod = "GET"

                        val response = StringBuilder()
                        Scanner(conn.inputStream).use { scanner ->
                            while (scanner.hasNextLine()) {
                                response.append(scanner.nextLine())
                            }
                        }

                        val json = JSONObject(response.toString())
                        val jsonArray = json.getJSONArray("meals")
                        for (i in 0 until jsonArray.length()) {
                            val mealJson = jsonArray.getJSONObject(i)

                            // getting all data and adding all retrieved Meal data to mutable list.
                            recipes = DataReceiver.getJsonData(mealJson, recipes)
                        }
                    }
                }
            }
        }
        catch (e: Exception)
        {
            // this method ( searchMealsByName() ) is invoked inside a coroutine(another lightweight
            // thread) to allow it to use the network.
            // so we need to switch to ui(main) thread before show an error message Dialog.
            runOnUiThread {
                // if an error occurred, show error message inside a alert dialog box.
                Messages.showError(
                    "Error",
                    "An Error Occurred While Retrieving the Data. ",
                    e.message.toString(),
                    this
                )
            }
        }
        // returning recipes mutable list.
        return recipes
    }
}
