package com.example.helloworld.features.doctor.statistics.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.helloworld.data.PatientDTO
import com.example.helloworld.data.PatientStatus

@Composable
fun StatisticScreen(
    navController: NavController,
    patients: List<PatientDTO>,
    modifier: Modifier = Modifier
) {
    val colors = mapOf<String, Color>(
        PatientStatus.Cured.name to Color.Green,
        PatientStatus.Relapse.name to Color.Red,
        PatientStatus.Remission.name to Color.Blue
    )
    Column(
        modifier = modifier.padding(bottom = 25.dp),
    ) {
        Icon(
            modifier = Modifier
                .size(40.dp)
                .padding(start = 15.dp, top = 10.dp)
                .alpha(0.6f)
                .clickable { navController.popBackStack() },
            imageVector = Icons.Filled.Close,
            contentDescription = null
        )
        LazyColumn(
            modifier = Modifier.padding(horizontal = 9.dp),

            ) {
            items(2) { index ->
                if (index == 0) {
                    Box(
                        modifier = modifier
                            .fillMaxWidth()
                            .heightIn(min = 400.dp)
                    )
                }
                if (index == 1) {
                    Column(
                        modifier = modifier.padding(vertical = 10.dp)
                    ) {
                        for (name in colors) {
                            Row(
                                modifier = modifier.padding(start = 15.dp, top = 15.dp)
                            ) {
                                Box(
                                    modifier = modifier
                                        .background(name.value)
                                        .height(20.dp)
                                        .width(40.dp)

                                )
                                Text(text = name.key, modifier.padding(start = 15.dp, end = 40.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}