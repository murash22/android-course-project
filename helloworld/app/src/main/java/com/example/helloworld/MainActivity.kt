package com.example.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.helloworld.features.doctor.DoctorScreen
import com.example.helloworld.features.general.password_screen.presentation.PasswordScreen
import com.example.helloworld.features.patient.PatientScreen
import com.example.helloworld.ui.theme.MainScreenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreenTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.PasswordScreen.route
    ) {

        composable(route = Routes.PasswordScreen.route) {
            PasswordScreen(navController = navController)
        }

        composable(
            route = Routes.Patient.route + "/{${Routes.Patient.argName}}",
            arguments = listOf(
                navArgument(Routes.Patient.argName ?: "id") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { entry ->
            PatientScreen(
                patientId = entry.arguments?.getString(PatientRoutes.TakeSurvey.argName) ?: ""
            )
        }

        composable(
            route = Routes.Doctor.route + "/{${Routes.Doctor.argName}}",
            arguments = listOf(
                navArgument(Routes.Doctor.argName ?: "id") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { entry ->
            DoctorScreen(
                doctorId = entry.arguments?.getString(Routes.Doctor.argName) ?: ""
            )
        }

    }

}