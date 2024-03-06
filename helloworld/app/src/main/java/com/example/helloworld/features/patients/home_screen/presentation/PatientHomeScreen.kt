package com.example.helloworld.features.patients.home_screen.presentation


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.helloworld.core.ui.bottom_nav_bar.BottomNavBar
import com.example.helloworld.core.ui.survey_card.SurveyCard
import com.example.helloworld.data.doctorSurveys


@Preview(
    showBackground = true,
    apiLevel = 33
)
@Composable
fun PatientHomeScreen(
    modifier: Modifier = Modifier
) {
    Scaffold (
        bottomBar = { BottomNavBar() },
    ) {paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(doctorSurveys) {
                SurveyCard(title = it.title, body = it.body)
            }
        }

    }
}

