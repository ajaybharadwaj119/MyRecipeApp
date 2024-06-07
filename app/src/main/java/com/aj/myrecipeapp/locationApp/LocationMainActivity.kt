package com.aj.myrecipeapp.locationApp

import android.content.Context
import android.os.Bundle
import android.Manifest
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aj.myrecipeapp.ui.theme.MyRecipeAppTheme

class LocationMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel :LocationViewModel = viewModel()
            MyRecipeAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp(viewModel)
                }
            }
        }
    }
}

@Composable
fun MyApp(viewModel: LocationViewModel){
    val context = LocalContext.current
    val locationUnit = LocationUtils(context)
    LocationDisplay(locationUnit = locationUnit,viewModel, context = context)
}

@Composable
fun LocationDisplay(
    locationUnit: LocationUtils,
    viewModel: LocationViewModel,
    context: Context
) {

    var textValue by remember { mutableStateOf("Searching for permission") }

  /*  val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult ={
            if (it[Manifest.permission.ACCESS_COARSE_LOCATION] == true &&
                it[Manifest.permission.ACCESS_FINE_LOCATION] == true
            ) {

            }
        }
    )*/

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            textValue = "Permission granted"
            // Permission granted, proceed with using location
        } else {
            textValue = "Permission not granted"
            // Permission denied, handle accordingly (e.g., show a message)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = textValue)

        Button(onClick = {
            if (locationUnit.hasLocationPermission(context)) {
                //permission already granted
                textValue = "Permission already granted"
            } else {
                //request permission
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
            }


        }) {
            Text(text = "Get Location")
        }

    }

}


