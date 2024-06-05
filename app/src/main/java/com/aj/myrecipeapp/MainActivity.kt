package com.aj.myrecipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aj.myrecipeapp.navigation.FirstScreen
import com.aj.myrecipeapp.navigation.SecondScreen
import com.aj.myrecipeapp.ui.theme.MyRecipeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MyRecipeAppTheme {
                /*RecipeScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),navController,{}
                )*/

                RecipeApp(navController = navController)
                
                //MyApp()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "firstScreen") {
        composable(route = "firstScreen"){
            FirstScreen {name->
                navController.navigate("secondScreen/$name")
            }
        }
        composable(route = "secondScreen/{name}"){
            val name = it.arguments?.getString("name") ?: "no name"
            SecondScreen(name) {
                navController.navigate("firstScreen")
            }
        }

       /* composable(route = "thirdScreen"){
            ThirdScreen {
                navController.navigate("firstScreen")
            }
        }*/
    }
}

