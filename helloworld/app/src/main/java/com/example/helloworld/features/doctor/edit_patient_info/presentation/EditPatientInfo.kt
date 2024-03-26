package com.example.helloworld.features.doctor.edit_patient_info.presentation

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.helloworld.R
import com.example.helloworld.data.PatientDTO
import com.example.helloworld.data.PatientStatus

@Composable
fun EditPatientInfoScreen(
    navController: NavController,
    patient: PatientDTO,
    modifier: Modifier = Modifier
) {
    val items = listOf(patient.name, patient.diagnosis, patient.status)
    var diagnosis by rememberSaveable { mutableStateOf(patient.diagnosis) }
    var status by rememberSaveable { mutableStateOf(patient.status.name) }
    val statusMap = mapOf<String, PatientStatus>(
        "вылечился" to PatientStatus.Cured,
        "ремиссия" to PatientStatus.Remission,
        "рецидив" to PatientStatus.Relapse
    )
    val context = LocalContext.current
    var white by rememberSaveable { mutableStateOf(true) }
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
            itemsIndexed(items) { index, content ->
                if (index == 0) {
                    Text(
                        modifier = Modifier.padding(vertical = 5.dp),
                        fontSize = 20.sp,
                        text = stringResource(R.string.edit_info, content),

                        )
                }
                if (index == 1) {
                    Text(
                        modifier = Modifier.padding(top = 30.dp, bottom = 8.dp),
                        fontSize = 15.sp,
                        text = stringResource(R.string.diagnoses)
                    )
                    OutlinedTextField(
                        value = diagnosis,
                        onValueChange = { input ->
                            diagnosis = input
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                        ),
                    )
                }
                if (index == 2) {
                    Text(
                        modifier = Modifier.padding(top = 30.dp, bottom = 8.dp),
                        fontSize = 15.sp,
                        text = stringResource(R.string.info_status)
                    )
                    OutlinedTextField(
                        value = status,
                        onValueChange = { input ->
                            status = input
                            white = true
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = if (white) Color.White else Color.Red,
                            focusedContainerColor = if (white) Color.White else Color.Red,
                        ),
                    )
                    TextButton(
                        modifier = Modifier
                            .padding(top = 200.dp, bottom = 100.dp)
                            .fillMaxWidth(),
                        border = BorderStroke(1.dp, Color.Black),
                        onClick = {
                            val temp_status = status.lowercase()
                            if (statusMap[temp_status] == null){
                                white = false
                            }
                            else {
                                white = true
                                patient.status = statusMap[status] ?: PatientStatus.Cured
                                patient.diagnosis = diagnosis
                                navController.popBackStack()
                            }
                        }
                    ) {
                        Text(
                            color = Color.Black,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(horizontal = 3.dp, vertical = 2.dp),
                            text = stringResource(R.string.save_info)
                        )
                    }
                }
            }
        }
    }
}
