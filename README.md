# Recipe-App
## This is a simple recipe app which uses a simple SQLite database, Kotlin and 'TheMealDB' web API to retrieve the recipies.

### @copyright Sandeepa Induwara Samaranayake - MOBILE APPLICATION DEVELOPMENT - Assignment 1 - Informatics Institute of Technology affiliated with the University of Westminster, UK.
The only libraries that used are the standard Android API libraries found in the following URL: https://developer.android.com/reference/

>+ The application developed will be helping users with meal preparation.
>+ The application will be using the https://www.themealdb.com/api.php/ Web service and the Room Library to save information about meals.

1. When the application starts, it presents the user with 4 buttons labelled Add Meals to DB, Search for Meals By Ingredient, Search for Meals and Search For Meals in Web.

<img src="https://github.com/SandeepaInduwaraSamaranayake/Recipe-App/assets/95087710/53f00213-cbff-46b6-86f5-1cd1dc213326"  style="display: inline-block; width: 40%;" />

2. Clicking on the Add Meals to DB button saves all the details of a few meals in an SQLite database local to the mobile device using the Room library. The specific information saved to the SQLite database is hardcoded in the application. You can access the hardcoded data from https://dracopd.users.ecs.westminster.ac.uk/DOCUM/courses/5cosc023w/meals.txt .

<img src="https://github.com/SandeepaInduwaraSamaranayake/Recipe-App/assets/95087710/a558a71a-be7e-4801-a7b8-429259d4e62e"  style="display: inline-block; width: 40%;" />

3.  The application is using the following Web service: https://www.themealdb.com/api.php/ . The documentation of how to use the Web service is in the above web page. For example, the following request (type this in your web browser): https://www.themealdb.com/api/json/v1/1/search.php?s=Arrabiata will give you back the results (in JSON format) of searching the meal with name “Arrabiata”. The result is like below:

<i>{"meals":[{"idMeal":"52771","strMeal":"Spicy Arrabiata Penne", "strDrinkAlternate":null,"strCategory":"Vegetarian","strArea":"Italian", "strInstructions":"Bring a large pot of water to a boil. Add kosher salt to the boiling water, then add the pasta. Cook according to the package instructions, about 9 minutes., "strTags":"Pasta,Curry", "strYoutube":"https:\/\/www.youtube.com\/watch?v=1IszT_guI08", "strIngredient1":"penne rigate","strIngredient2":"olive oil", "strIngredient3":"garlic", "strIngredient4":"chopped tomatoes","strIngredient5":"red chile flakes",}] .....}</i>

4. Clicking on the Search for Meals By Ingredient button will present the user with a screen displaying a single textbox and 2 buttons Retrieve Meals and Save meals to Database.

<img src="https://github.com/SandeepaInduwaraSamaranayake/Recipe-App/assets/95087710/547e3b26-113b-4aca-9d86-638cc0c19062"  style="display: inline-block; width: 40%;" />

5. 

